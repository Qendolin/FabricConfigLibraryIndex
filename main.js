const { createApp } = Vue;

const DEPENDENCIES_MAP = {
	FABRIC_API: { name: 'Fabric API', url: 'https://github.com/FabricMC/fabric-api' },
	FABRIC_LANGUAGE_KOTLIN: {
		name: 'Fabric Language Kotlin',
		url: 'https://github.com/FabricMC/fabric-language-kotlin',
	},
	MALILIB: { name: 'MaLiLib', url: 'https://github.com/sakura-ryoko/malilib' },
	UI_LIB: { name: 'UI Lib', url: 'https://github.com/DAQEM/UILib' },
	MOD_MENU: { name: 'Mod Menu', url: 'https://github.com/TerraformersMC/ModMenu' },
	YET_ANOTHER_CONFIG_LIB: { name: 'Yacl', url: 'https://github.com/isXander/YetAnotherConfigLib' },
	PLACEHOLDER_API: { name: 'Text Placeholder API', url: 'https://github.com/Patbox/TextPlaceholderAPI' },
};

// Parses a version string into an array of integers [major, minor, patch]
function parseVersion(v) {
	const parts = v.split('.').map((x) => {
		const val = parseInt(x, 10);
		return isNaN(val) ? 999 : val; // Safe fallback for 'x' or wildcards
	});
	while (parts.length < 3) {
		parts.push(0); // 1.20 automatically becomes [1, 20, 0]
	}
	return parts;
}

// Compares two version arrays chronologically (lowest to highest)
function compareVersions(a, b) {
	const pa = parseVersion(a);
	const pb = parseVersion(b);
	for (let i = 0; i < 3; i++) {
		if (pa[i] !== pb[i]) {
			return pa[i] - pb[i];
		}
	}
	return 0;
}

// Collapses contiguous sequences of version patches into ranges (e.g. 1.21 - 1.21.5)
function compactVersions(versions) {
	if (!versions || versions.length === 0) return [];

	// Sort array from lowest to highest first
	const sorted = [...versions].sort(compareVersions);

	const result = [];
	let rangeStart = sorted[0];
	let prev = sorted[0];

	for (let i = 1; i < sorted.length; i++) {
		const current = sorted[i];
		const pPrev = parseVersion(prev);
		const pCurrent = parseVersion(current);

		// Merge only if major & minor are identical, and patch increments strictly by 1
		if (pCurrent[0] === pPrev[0] && pCurrent[1] === pPrev[1] && pCurrent[2] === pPrev[2] + 1) {
			prev = current; // Extend the current contiguous range
		} else {
			// Commit the finished range
			if (rangeStart === prev) {
				result.push(rangeStart);
			} else {
				result.push(`${rangeStart} - ${prev}`);
			}
			rangeStart = current;
			prev = current;
		}
	}

	// Commit the final range
	if (rangeStart === prev) {
		result.push(rangeStart);
	} else {
		result.push(`${rangeStart} - ${prev}`);
	}

	return result;
}

createApp({
	data() {
		return {
			libraries: [],
			activeFilters: {},
			isGridView: true,
			loading: true,
			theme: 'dark',
		};
	},
	computed: {
		// Collects version nodes dynamically, mapping older ones safely
		extractedVersions() {
			const versions = new Set();
			this.libraries.forEach((lib) => {
				const list = lib.condensedVersions || lib.versions || [];
				list.forEach((v) => versions.add(v));
			});
			return Array.from(versions).sort(compareVersions);
		},
		versionGroups() {
			const groups = {};
			this.extractedVersions.forEach((v) => {
				const major = v.split('.').slice(0, 2).join('.');
				if (!groups[major]) groups[major] = [];
				groups[major].push(v);
			});
			return groups;
		},
		extractedSides() {
			const sides = new Set();
			this.libraries.forEach((lib) => {
				if (lib.side && lib.side !== 'UNKNOWN') sides.add(lib.side);
			});
			return Array.from(sides);
		},
		extractedConfigTypes() {
			const types = new Map();
			this.libraries.forEach((lib) => {
				(lib.extraConfigTypes || []).forEach((t) => {
					if (typeof t === 'string' && t !== 'UNKNOWN') {
						types.set(t, this.formatId(t));
					} else if (t && typeof t === 'object') {
						const id = t.id || t.name;
						if (id && id !== 'UNKNOWN') types.set(id, t.name || this.formatId(id));
					}
				});
			});
			return Array.from(types.entries()).map(([id, name]) => ({ id, name }));
		},
		extractedFeatures() {
			const features = new Set();
			this.libraries.forEach((lib) => {
				(lib.extraFeatures || []).forEach((f) => {
					if (f !== 'UNKNOWN') features.add(f);
				});
			});
			return Array.from(features).map((f) => ({ id: f, name: this.formatId(f) }));
		},
		extractedFormats() {
			const formats = new Set();
			this.libraries.forEach((lib) => {
				(lib.configFormats || []).forEach((f) => {
					if (f !== 'UNKNOWN') formats.add(f);
				});
			});
			return Array.from(formats).map((f) => ({ id: f, name: this.formatId(f) }));
		},
		extractedInitModes() {
			const modes = new Set();
			this.libraries.forEach((lib) => {
				if (
					lib.manualInitialization &&
					lib.manualInitialization !== 'NOT_AVAILABLE' &&
					lib.manualInitialization !== 'UNKNOWN'
				) {
					modes.add(lib.manualInitialization);
				}
			});
			return Array.from(modes).map((m) => ({ id: m, name: this.getManualInitName(m) }));
		},
		extractedWaaas() {
			const waaas = new Map();
			this.libraries.forEach((lib) => {
				if (lib.configMethod && Array.isArray(lib.configMethod.waaas)) {
					lib.configMethod.waaas.forEach((w) => {
						const id = w.id || w.name;
						if (id && id !== 'UNKNOWN') {
							waaas.set(id, w.name || this.formatId(id));
						}
					});
				}
			});
			return Array.from(waaas.entries()).map(([id, name]) => ({ id, name }));
		},
		extractedUiMethods() {
			const methods = new Set();
			this.libraries.forEach((lib) => {
				if (lib.uiMethod && lib.uiMethod !== 'UNKNOWN') methods.add(lib.uiMethod);
			});
			return Array.from(methods).map((m) => ({ id: m, name: this.getUiMethodName(m) }));
		},
		filteredLibraries() {
			return this.libraries.filter((lib) => {
				for (const [category, valuesSet] of Object.entries(this.activeFilters)) {
					if (valuesSet.size === 0) continue;

					let libValues = [];
					if (category === 'versions') libValues = lib.condensedVersions || lib.versions || [];
					else if (category === 'side') libValues = [lib.side];
					else if (category === 'extraConfigTypes') {
						libValues = (lib.extraConfigTypes || []).map((t) =>
							typeof t === 'string' ? t : t.id || t.name,
						);
					} else if (category === 'extraFeatures') libValues = lib.extraFeatures || [];
					else if (category === 'configFormats') libValues = lib.configFormats || [];
					else if (category === 'manualInitialization') libValues = [lib.manualInitialization];
					else if (category === 'uiMethod') libValues = [lib.uiMethod];
					else if (category === 'configMethod.instance') {
						const mt = (lib.configMethod?.memberType || '').toUpperCase();
						if (mt.includes('INSTANCE') || mt === 'EITHER') libValues.push('true');
						if (mt.includes('STATIC') || mt === 'EITHER') libValues.push('false');
					} else if (category === 'configMethod.waaas') {
						libValues = (lib.configMethod?.waaas || []).map((w) => w.id || w.name);
					}

					const isOrFilter = [
						'configFormats',
						'configMethod.waaas',
						'uiMethod',
						'manualInitialization',
					].includes(category);
					const listValues = Array.from(valuesSet);

					if (isOrFilter) {
						const hasMatch = listValues.some((val) =>
							libValues.some((x) => String(x).toLowerCase() === val.toLowerCase()),
						);
						if (!hasMatch) return false;
					} else {
						const hasAll = listValues.every((val) => {
							if (category === 'side') {
								return (
									libValues.some((x) => String(x).toLowerCase() === val.toLowerCase()) ||
									libValues.includes('BOTH')
								);
							}
							return libValues.some((x) => String(x).toLowerCase() === val.toLowerCase());
						});
						if (!hasAll) return false;
					}
				}
				return true;
			});
		},
	},
	methods: {
		async fetchData() {
			try {
				// Dynamic JSON catalog [1]
				const res = await fetch('https://kr1v.net/libs/libs.json');
				const data = await res.json();
				const list = Object.values(data);

				list.forEach((lib) => {
					if (Array.isArray(lib.dependencies)) {
						lib.dependencies = lib.dependencies.map((depName) => {
							const upperKey = String(depName).toUpperCase();
							const mapped = DEPENDENCIES_MAP[upperKey];
							return {
								id: upperKey,
								name: mapped ? mapped.name : this.formatId(depName),
								url: mapped ? mapped.url : '',
							};
						});
					} else {
						lib.dependencies = [];
					}
				});

				this.libraries = list;
				this.loading = false;
			} catch (e) {
				console.error('Failed to load libraries JSON', e);
				this.loading = false;
			}
		},
		toggleTheme() {
			this.theme = this.theme === 'dark' ? 'light' : 'dark';
			document.documentElement.setAttribute('data-theme', this.theme);
			localStorage.setItem('theme', this.theme);
		},
		toggleFilter(category, value) {
			if (!this.activeFilters[category]) {
				this.activeFilters[category] = new Set();
			}
			const set = this.activeFilters[category];
			if (set.has(value)) {
				set.delete(value);
			} else {
				set.add(value);
			}
		},
		isFilterActive(category, value) {
			return this.activeFilters[category]?.has(value) || false;
		},
		resetFilters() {
			this.activeFilters = {};
		},
		formatId(id) {
			if (!id) return '';
			return id
				.split('_')
				.map((w) => w.charAt(0).toUpperCase() + w.slice(1).toLowerCase())
				.join(' ');
		},
		getFormattedVersions(lib) {
			const list = lib.condensedVersions || lib.versions || [];
			return compactVersions(list);
		},
		getManualInitName(val) {
			const map = {
				YES: 'Yes',
				AT_MOD_INIT: 'At mod initialization',
				OPTIONAL: 'Optional',
				NO: 'No',
				NOT_AVAILABLE: 'n/a',
				UNKNOWN: '?',
			};
			return map[val?.toUpperCase()] || this.formatId(val);
		},
		getUiMethodName(val) {
			const map = {
				BUILDER: 'Builder',
				AUTOMATIC: 'Automatic',
				MANUAL: 'Manual',
				CUSTOM_SCREEN_CLASS: 'Manual',
				COMMANDS: 'Commands',
				NONE: 'None',
			};
			return map[val?.toUpperCase()] || this.formatId(val);
		},
		isConfigMethodAvailable(lib) {
			const cm = lib.configMethod;
			if (!cm || cm === 'NOT_AVAILABLE' || cm === 'UNKNOWN') return false;

			// If it's a serialized object, verify that at least one property is populated
			if (typeof cm === 'object') {
				return (
					cm.typeOfClass !== null ||
					cm.memberType !== null ||
					(Array.isArray(cm.waaas) && cm.waaas.length > 0)
				);
			}
			return true;
		},
		// Reconstructs configMethod formatted strings dynamically [1]
		formatConfigMethod(lib) {
			if (lib.configMethodFormatted) return lib.configMethodFormatted;
			if (!lib.configMethod || lib.configMethod === 'NOT_AVAILABLE' || lib.configMethod === 'UNKNOWN')
				return 'Unknown';

			const cm = lib.configMethod;
			let formatted = '';

			if (cm.typeOfClass && cm.typeOfClass !== 'NONE') {
				const article = ['EXTENDING', 'ANNOTATED'].includes(cm.typeOfClass.toUpperCase()) ? 'an' : 'a';
				formatted += `${article} ${this.formatId(cm.typeOfClass).toLowerCase()} class with `;
			}

			const mtDesc =
				{
					INSTANCE: 'instance members',
					STATIC: 'static members',
					EITHER: 'static or instance members',
					NONE: '',
				}[cm.memberType?.toUpperCase()] ||
				cm.memberType ||
				'';
			if (mtDesc) formatted += mtDesc;

			if (Array.isArray(cm.waaas) && cm.waaas.length > 0) {
				const parts = cm.waaas.map((w) => {
					const desc = w.methodDescription || this.formatId(w.name);
					const prefix =
						w.name?.toUpperCase() === 'ANNOTATED_PRIMITIVE' || w.name === 'Annotated primitive'
							? 'annotated '
							: '';
					return `${prefix}${desc}`;
				});

				if (parts.length === 1) formatted += `, ${parts[0]}`;
				else if (parts.length === 2) formatted += `, either ${parts[0]} or ${parts[1]}`;
				else {
					const last = parts.pop();
					formatted += `, either ${parts.join(', ')}, or ${last}`;
				}
			}
			return formatted;
		},
		getWaaaExamples(lib) {
			if (!lib.configMethod || !Array.isArray(lib.configMethod.waaas)) return [];
			return lib.configMethod.waaas.flatMap((w) => w.examples || []).slice(0, 3);
		},
		toggleCode(libId) {
			const el = document.getElementById('example-config-' + libId);
			if (el) el.classList.toggle('hidden');
		},
	},
	mounted() {
		const savedTheme = localStorage.getItem('theme');
		const systemPrefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
		this.theme = savedTheme || (systemPrefersDark ? 'dark' : 'light');
		this.fetchData();
	},
}).mount('#app');
