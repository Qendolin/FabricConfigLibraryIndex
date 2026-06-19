import matter from 'gray-matter';
import yaml from 'js-yaml';
import { expandGlob } from '@std/fs';
import { basename, extname } from '@std/path';
import { MetadataEntry } from '../types.ts';

export async function parseYamlFile<T>(filepath: string): Promise<T> {
	const text = await Deno.readTextFile(filepath);
	const { data, content } = matter(text);
	const body = content.trim() ? (yaml.load(content) as any) : {};

	// If the parsed body is an array (like in facts.yml), return it directly
	if (Array.isArray(body)) {
		return body as unknown as T;
	}

	return { ...data, ...body } as T;
}

export async function loadMetadata(dataDir: string): Promise<Record<string, MetadataEntry[]>> {
	const metadata: Record<string, MetadataEntry[]> = {};
	for await (const file of expandGlob(`${dataDir}/*.yml`)) {
		const raw = await parseYamlFile<Record<string, any>>(file.path);
		const key = basename(file.name, extname(file.name));
		metadata[key] = Object.entries(raw)
			.filter(([k]) => !['version', 'description', 'created'].includes(k))
			.map(([id, val]) => ({ id, ...val }));
	}
	return metadata;
}
