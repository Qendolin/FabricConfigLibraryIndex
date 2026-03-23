const activeFilters = new Map();
let libraryData = {};

fetch('https://raw.githubusercontent.com/kr1viah/FabricConfigLibraryIndex/refs/heads/master/libs.json').then(r => r.json()).then(data => { libraryData = data; });

function toggleFilter(category, value) {
    const span = event.currentTarget;
    if (!activeFilters.has(category)) activeFilters.set(category, new Set());
    const set = activeFilters.get(category);

    if (set.has(value)) {
        set.delete(value);
        span.classList.remove('active');
    } else {
        set.add(value);
        span.classList.add('active');
    }

    if (set.size === 0) activeFilters.delete(category);
    filterPanels();
}

function resetFilters() {
    document.querySelectorAll('span.tag.active').forEach(t => t.classList.remove('active'));
    activeFilters.clear();
    filterPanels();
}

function panelMatches(lib, category, values) {
    switch (category) {
        case 'versions':
            return [...values].every(v => lib.versions?.includes(v));

        case 'side':
            return [...values].every(v => lib.side === v || lib.side === 'BOTH');

        case 'extraConfigTypes':
            return [...values].every(v => lib.extraConfigTypes?.includes(v));

        case 'extraFeatures':
            return [...values].every(v => lib.extraFeatures?.includes(v));

        case 'configFormats':
            return [...values].some(v => lib.configFormats?.includes(v));

        case 'manualInitialization':
            return values.has(lib.manualInitialization);

        case 'configMethod.instance':
            if (!lib.configMethod) return false;
            return values.has(String(lib.configMethod.instance));

        case 'configMethod.waaas':
            return [...values].some(v => lib.configMethod?.waaas?.includes(v));

        default:
            return true;
    }
}

function filterPanels() {
    document.querySelectorAll('.library_grid .panel').forEach(panel => {
        const lib = libraryData[panel.id];
        if (!lib) return;
        const visible = [...activeFilters.entries()]
            .every(([cat, vals]) => panelMatches(lib, cat, vals));
        panel.style.display = visible ? '' : 'none';
    });
}