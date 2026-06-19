export async function fetchModrinthVersions(slug: string, mcVersions: string[]): Promise<string[]> {
	const url = `https://api.modrinth.com/v2/project/${slug}/version`;
	const res = await fetch(url);
	if (!res.ok) {
		console.warn(`Failed to fetch modrinth versions for slug: ${slug}`);
		return [];
	}
	const data = await res.json();

	const fabricVersions = data
		.filter((v: any) => Array.isArray(v.loaders) && v.loaders.includes('fabric'))
		.flatMap((v: any) => v.game_versions);

	// Filter against valid MC list, distinct, and sort utilizing the ALL_LIST index mechanism
	const uniqueVersions = Array.from(new Set<string>(fabricVersions));
	const supported = uniqueVersions.filter((v) => mcVersions.includes(v));

	supported.sort((a, b) => mcVersions.indexOf(a) - mcVersions.indexOf(b));
	return supported;
}
