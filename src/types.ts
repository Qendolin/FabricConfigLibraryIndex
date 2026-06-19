export interface Dependency {
	name: string;
	url?: string;
	id?: string;
}

export interface ConfigMethodWaaa {
	id: string;
	name: string;
	displayName?: string;
	methodDescription?: string;
	examples?: string[];
}

export interface ConfigMethod {
	typeOfClass: string;
	typeOfClassName?: string;
	memberType: string;
	memberTypeDesc?: string;
	waaas: ConfigMethodWaaa[];
	randomExamples?: string[];
}

export interface ExtraConfigType {
	id: string;
	name: string;
	description?: string;
}

export interface ConfigLibrary {
	id: string;
	name: string;
	side: 'CLIENT' | 'SERVER' | 'BOTH' | 'UNKNOWN';
	modrinthSlug?: string;
	type: 'LOADER' | 'UI' | 'BOTH' | 'UNKNOWN';
	versions: string[] | 'ALL_LIST';
	dependencies?: Dependency[];
	extraConfigTypes?: ExtraConfigType[];
	extraFeatures?: string[];
	configFormats?: string[];
	manualInitialization: string;
	configMethod?: ConfigMethod | 'NOT_AVAILABLE' | 'UNKNOWN';
	configMethodFormatted?: string;
	uiMethod: string;
	notes?: string[];
	source: string;
	exampleConfigClass?: string;

	// Attached dynamically at build time:
	condensedVersions?: string[];
	tags?: { name: string; tooltip?: string }[];
	hasScreenshot?: boolean;
	manualInitName?: string;
	uiMethodName?: string;
}

export interface MetadataEntry {
	id: string;
	name?: string;
	description?: string;
	url?: string;
	examples?: string[];
	methodDescription?: string;
	[key: string]: unknown;
}

export interface Fact {
	title: string;
	date: string;
	content: string;
	day?: number;
}
