export async function fetchMojangVersions() {
	const url = 'https://piston-meta.mojang.com/mc/game/version_manifest_v2.json';
	const res = await fetch(url);
	const data = await res.json();

	// Get all releases, oldest first
	const releases = data.versions
		.filter((v: any) => v.type === 'release')
		.map((v: any) => v.id)
		.reverse();

	// Drop oldest 55, then reverse back to newest first
	const allList = releases.slice(55).reverse();

	const allSet: string[][] = [];
	// Loop from oldest to newest to build chunks
	for (const version of [...allList].reverse()) {
		const parts = version.split('.');
		if (parts.length === 2) {
			allSet.push([]);
		}
		if (allSet.length > 0) {
			allSet[allSet.length - 1].push(version);
		}
	}
	// Reverse chunks to newest major first
	allSet.reverse();

	return { ALL_LIST: allList, ALL_SET: allSet };
}

export function condenseVersions(versions: string[], allSet: string[][]): string[] {
	const newVersions: string[] = [];
	let currentMajor: string[] = [];
	let lastMajor = '0';
	let lastMinor = '0';

	const isSameArray = (a: string[], b: string[]) =>
		a.length === b.length && a.every((val, index) => val === b[index]);

	for (const version of [...versions].reverse()) {
		const parts = version.split('.');
		if (!(lastMajor === parts[0] && lastMinor === (parts[1] || '0'))) {
			if (currentMajor.length > 0) {
				const isFullSet = allSet.some((set) => isSameArray(set, currentMajor));
				if (isFullSet) {
					newVersions.push(`${currentMajor[0]}.x`);
				} else {
					newVersions.push(...currentMajor);
				}
			}
			currentMajor = [];
		}
		currentMajor.push(version);
		lastMajor = parts[0];
		lastMinor = parts[1] || '0';
	}

	if (currentMajor.length > 0) {
		const isFullSet = allSet.some((set) => isSameArray(set, currentMajor));
		if (isFullSet) {
			newVersions.push(`${currentMajor[0]}.x`);
		} else {
			newVersions.push(...currentMajor);
		}
	}

	return newVersions;
}
