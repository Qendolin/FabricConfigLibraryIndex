const activeFilters = new Map();

function toggleFilter(category, value, el) {
	if (!activeFilters.has(category)) activeFilters.set(category, new Set());
	const set = activeFilters.get(category);

	if (set.has(value)) {
		set.delete(value);
		el.classList.remove('active');
	} else {
		set.add(value);
		el.classList.add('active');
	}

	if (set.size === 0) activeFilters.delete(category);
	filterPanels();
}

function resetFilters() {
	document.querySelectorAll('.filter-tag.active').forEach((t) => t.classList.remove('active'));
	activeFilters.clear();
	filterPanels();
}

function panelMatches(lib, category, values) {
	switch (category) {
		case 'versions':
			return [...values].every((v) => lib.versions?.includes(v) || lib.condensedVersions?.includes(v));

		case 'side':
			return [...values].every((v) => lib.side === v || lib.side === 'BOTH');

		case 'extraConfigTypes':
			return [...values].every(
				(v) =>
					lib.extraConfigTypes?.includes(v) || lib.extraConfigTypes?.some((t) => t.id === v || t.name === v),
			);

		case 'extraFeatures':
			return [...values].every((v) => lib.extraFeatures?.includes(v));

		case 'configFormats':
			return [...values].some((v) => lib.configFormats?.includes(v));

		case 'manualInitialization':
			return values.has(lib.manualInitialization);

		case 'configMethod.instance': {
			if (!lib.configMethod || lib.configMethod === 'NOT_AVAILABLE' || lib.configMethod === 'UNKNOWN')
				return false;
			const memberType = String(lib.configMethod.memberType);
			if (!memberType) return false;
			if (memberType === 'EITHER') return true;
			if (values.has('true')) return memberType === 'INSTANCE';
			return memberType === 'STATIC';
		}
		case 'configMethod.waaas':
			if (!lib.configMethod || !Array.isArray(lib.configMethod.waaas)) return false;
			return [...values].some((v) => lib.configMethod.waaas.some((s) => s.name === v));

		case 'uiMethod':
			return [...values].some((v) => lib.uiMethod === v);

		default:
			return true;
	}
}

function filterPanels() {
	document.querySelectorAll('.library-card').forEach((panel) => {
		const lib = JSON.parse(panel.getAttribute('data-lib') || '{}');
		const visible = [...activeFilters.entries()].every(([cat, vals]) => panelMatches(lib, cat, vals));
		panel.classList.toggle('hidden', !visible);
	});
}

function toggleCode(id) {
	document.getElementById('example-config-' + id).classList.toggle('hidden');
}
