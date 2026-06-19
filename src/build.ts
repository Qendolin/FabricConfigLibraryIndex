import { ensureDir, expandGlob } from '@std/fs';
import { join, basename, extname } from '@std/path';
import Handlebars from 'handlebars';
import { fetchMojangVersions, condenseVersions } from './api/mojang.ts';
import { fetchModrinthVersions } from './api/modrinth.ts';
import { loadMetadata, parseYamlFile } from './utils/parser.ts';
import { ConfigLibrary, Fact, MetadataEntry, ConfigMethodWaaa, ConfigMethod } from './types.ts';

function normalizeCodeIndentation(code: string): string {
	const lines = code.split('\n');
	if (lines.length <= 1) return code;

	// Find the indentation level of all non-empty lines (excluding the first line)
	const indents = lines
		.slice(1)
		.filter((line) => line.trim().length > 0)
		.map((line) => {
			const match = line.match(/^(\s*)/);
			return match ? match[0].length : 0;
		});

	if (indents.length === 0) return code;
	const minIndent = Math.min(...indents);

	// A standard Java class body should be indented by 4 spaces.
	// If the actual minimum indent is greater than 4 spaces (e.g. 18 spaces),
	// we slice off the excess indentation so it looks beautiful.
	const targetIndent = 0;
	if (minIndent > targetIndent) {
		const excess = minIndent - targetIndent;
		return [
			lines[0], // Keep the first line (class declaration) as-is
			...lines.slice(1).map((line) => (line.length >= excess ? line.slice(excess) : line)),
		].join('\n');
	}

	return code;
}

async function main(): Promise<void> {
	const { ALL_LIST, ALL_SET } = await fetchMojangVersions();
	const metadata = await loadMetadata('data');

	const libraries: ConfigLibrary[] = [];
	for await (const file of expandGlob('data/libraries/*.yml')) {
		libraries.push(await parseYamlFile<ConfigLibrary>(file.path));
	}

	const formatId = (id: string): string =>
		id
			.split('_')
			.map((w) => w.charAt(0) + w.slice(1).toLowerCase())
			.join(' ');

	const getMeta = (cat: string, id: string): MetadataEntry => {
		const meta = metadata[cat]?.find((m) => m.id === id);
		return meta ? { ...meta, name: meta.name || formatId(meta.id) } : { id, name: formatId(id), description: '' };
	};

	for (const lib of libraries) {
		if (lib.modrinthSlug) {
			lib.versions = await fetchModrinthVersions(lib.modrinthSlug, ALL_LIST);
			try {
				const stat = await Deno.stat(`data/screenshots/${lib.modrinthSlug}.png`);
				lib.hasScreenshot = stat.isFile;
			} catch {
				lib.hasScreenshot = false;
			}
		} else if (lib.versions === 'ALL_LIST') {
			lib.versions = [...ALL_LIST];
		} else if (!lib.versions) {
			lib.versions = [];
		}
		lib.condensedVersions = condenseVersions(lib.versions as string[], ALL_SET);

		if (lib.dependencies) {
			lib.dependencies = lib.dependencies.map((dep) => {
				const meta = getMeta('dependencies', dep.name);
				return {
					id: dep.name,
					name: meta.name || dep.name,
					url: meta.url || dep.url || '',
				};
			});
		}

		if (lib.exampleConfigClass) {
			const cleanCode = lib.exampleConfigClass.replace(/\t/g, '  ');
			lib.exampleConfigClass = normalizeCodeIndentation(cleanCode);
		}
		lib.manualInitName = getMeta('init-modes', lib.manualInitialization).name;
		lib.uiMethodName = getMeta('ui-methods', lib.uiMethod).name;

		if (lib.configMethod && lib.configMethod !== 'NOT_AVAILABLE' && lib.configMethod !== 'UNKNOWN') {
			const cm = lib.configMethod as ConfigMethod;
			const rootMeta = metadata['config-methods'] || [];

			const tocMeta =
				(rootMeta.find((m) => m.id === 'TypeOfClass') as unknown as Record<string, { name?: string }>) || {};
			const mtMeta =
				(rootMeta.find((m) => m.id === 'MemberType') as unknown as Record<string, { description?: string }>) ||
				{};
			const waaaMeta =
				(rootMeta.find((m) => m.id === 'Waaa') as unknown as Record<string, ConfigMethodWaaa>) || {};

			cm.typeOfClassName = tocMeta[cm.typeOfClass]?.name || cm.typeOfClass;
			cm.memberTypeDesc = mtMeta[cm.memberType]?.description || cm.memberType;

			// 1. Build class prefix (e.g. "a normal class with ")
			let formatted = '';
			if (cm.typeOfClass && cm.typeOfClass !== 'NONE') {
				const article = ['EXTENDING', 'ANNOTATED'].includes(cm.typeOfClass) ? 'an' : 'a';
				formatted += `${article} ${(cm.typeOfClassName || '').toLowerCase()} class with `;
			}

			// 2. Add member scope (e.g. "static or instance members")
			if (cm.memberTypeDesc && cm.memberType !== 'NONE') {
				formatted += cm.memberTypeDesc;
			}

			// 3. Collect & dynamically format multiple Waaas with proper grammatical joins
			if (Array.isArray(cm.waaas) && cm.waaas.length > 0) {
				const allExamples: string[] = [];

				const parts = cm.waaas.map((w) => {
					const matchKey = Object.keys(waaaMeta).find((k) => k.toLowerCase() === w.name?.toLowerCase());
					const wMeta: ConfigMethodWaaa = matchKey ? waaaMeta[matchKey] : ({} as any);

					const examples = w.examples || wMeta.examples || [];
					allExamples.push(...examples);

					const desc = w.methodDescription || wMeta.methodDescription || w.name;
					const prefix = w.name === 'Annotated primitive' ? 'annotated ' : '';
					return `${prefix}${desc}`;
				});

				cm.randomExamples = allExamples.sort(() => 0.5 - Math.random()).slice(0, 3);

				// Join items with "either", commas, and "or" depending on count
				if (parts.length === 1) {
					formatted += `, ${parts[0]}`;
				} else if (parts.length === 2) {
					formatted += `, either ${parts[0]} or ${parts[1]}`;
				} else {
					const last = parts.pop();
					formatted += `, either ${parts.join(', ')}, or ${last}`;
				}
			}

			lib.configMethodFormatted = formatted;
		}

		const tags: { name: string; tooltip?: string }[] = [];
		if (lib.side === 'CLIENT' || lib.side === 'BOTH') tags.push({ name: 'Client' });
		if (lib.side === 'SERVER' || lib.side === 'BOTH') tags.push({ name: 'Server' });
		if (lib.type === 'UI' || lib.type === 'BOTH') tags.push({ name: 'UI' });
		if (lib.type === 'LOADER' || lib.type === 'BOTH') tags.push({ name: 'Loader' });

		const extraFeatures = Array.isArray(lib.extraFeatures)
			? lib.extraFeatures
			: lib.extraFeatures
				? [lib.extraFeatures]
				: [];
		const configFormats = Array.isArray(lib.configFormats)
			? lib.configFormats
			: lib.configFormats
				? [lib.configFormats]
				: [];

		// Ignore "UNKNOWN" values so they don't generate generic tags
		extraFeatures
			.filter((f) => f !== 'UNKNOWN')
			.forEach((f) => {
				const meta = getMeta('features', f);
				tags.push({ name: meta.name || f, tooltip: meta.description || meta.name || f });
			});

		configFormats
			.filter((f) => f !== 'UNKNOWN')
			.forEach((f) => {
				tags.push({ name: getMeta('config-formats', f).name || f });
			});

		lib.tags = tags;
	}

	libraries.sort((a, b) => a.name.localeCompare(b.name));

	let facts: Fact[] = [];
	try {
		const rawFacts = await parseYamlFile<Fact[]>('data/facts.yml');
		if (Array.isArray(rawFacts)) {
			facts = rawFacts.map((f, i) => ({ ...f, day: i + 1 }));
		}
	} catch {
		// Ignore missing facts.yml
	}

	Handlebars.registerHelper('join', (arr: unknown[], s: string) => arr?.join(s));
	Handlebars.registerHelper('eq', (a: unknown, b: unknown) => a === b);
	Handlebars.registerHelper('notEq', (a: unknown, b: unknown) => a !== b);
	Handlebars.registerHelper('or', (a: unknown, b: unknown) => a || b);
	Handlebars.registerHelper('and', (a: unknown, b: unknown) => a && b);
	Handlebars.registerHelper('lower', (s: unknown) => (typeof s === 'string' ? s.toLowerCase() : s));
	Handlebars.registerHelper('json', (ctx: unknown) => JSON.stringify(ctx || {}));

	for await (const file of expandGlob('src/templates/partials/*.hbs')) {
		Handlebars.registerPartial(basename(file.name, extname(file.name)), await Deno.readTextFile(file.path));
	}
	Handlebars.registerPartial('layout', await Deno.readTextFile('src/templates/layout.hbs'));

	await ensureDir('generated/json');
	const libsObj: Record<string, ConfigLibrary> = {};
	libraries.forEach((l) => (libsObj[l.id] = l));
	await Deno.writeTextFile('generated/libs.json', JSON.stringify(libsObj, null, 2));

	await ensureDir('generated/screenshots');
	try {
		for await (const file of expandGlob('data/screenshots/*.png')) {
			await Deno.copyFile(file.path, join('generated/screenshots', file.name));
		}
	} catch {}

	const waaaObj =
		(metadata['config-methods']?.find((m) => m.id === 'Waaa') as unknown as Record<
			string,
			{ name?: string; examples?: string[] }
		>) || {};
	const configMethodWaaas = Object.entries(waaaObj)
		.filter(([k]) => k !== 'id')
		.map(([k, v]) => ({ id: k, name: v.name || k, examples: v.examples || [] }));

	const initModes = metadata['init-modes']?.map((m) => ({ ...m, name: m.name || formatId(m.id) })) || [];

	const compilePage = async (srcPath: string, destPath: string, context: Record<string, unknown>): Promise<void> => {
		const tpl = Handlebars.compile(await Deno.readTextFile(srcPath));
		await Deno.writeTextFile(destPath, tpl(context));
	};

	await compilePage('src/templates/index.hbs', 'generated/index.html', {
		title: 'Fabric Config Library Index',
		baseHref: './',
		libraries,
		allSet: ALL_SET,
		configTypes: metadata['config-types'],
		features: metadata['features'],
		configFormats: metadata['config-formats'],
		initModes,
		uiMethods: metadata['ui-methods'],
		configMethodWaaas,
	});

	await compilePage('src/templates/facts.hbs', 'generated/facts.html', {
		title: 'Facts',
		baseHref: './',
		facts: [...facts].reverse(),
	});

	await ensureDir('generated/facts');
	for (const fact of facts) {
		await compilePage('src/templates/fact_single.hbs', `generated/facts/${fact.day}.html`, {
			title: `Day ${fact.day}`,
			baseHref: '../',
			fact,
		});
	}

	await Deno.copyFile('src/styles/main.css', 'generated/main.css');
	await Deno.copyFile('src/scripts/main.js', 'generated/main.js');
	console.log(`Built ${libraries.length} libs.`);
}

main();
