# YAML Schema Documentation

This document describes the schema for all YAML files in the `data` directory.

---

## Metadata Files (data/*.yml)

These files define the enumeration values and metadata used throughout the library index.

### Common Frontmatter
All metadata files share this structure:

```yaml
---
# YAML frontmatter with library metadata
version: "1.0.0"
description: "Description of what this file defines"
created: "YYYY-MM-DD"
---
```

### 1. data/config-formats.yml
**Purpose**: Defines supported configuration file formats.

**Structure**:
```yaml
<FORMAT_NAME>:
  description: "Description of the format"
  name: "Display name"
```

**Example**:
```yaml
JSON:
  description: "JSON format for configuration files"
  name: "Json"
```

**Allowed Values**:
- `JSON` → name: `"Json"`
- `TOML` → name: `"Toml"`
- `YAML` → name: `"Yaml"`
- `NOT_AVAILABLE` → name: `"n/a"`
- `PROPERTIES` → name: `".properties"`
- `INI` → name: `".ini"`
- `JSON5` → name: `"Json5"`
- `HOCON` → name: `"Hocon"`
- `JSONC` → name: `"JsonC"`

---

### 2. data/config-methods.yml
**Purpose**: Defines how configuration values are accessed within mod classes.

**Structure**:
```yaml
<METHOD_CATEGORY>:
  <METHOD_TYPE>:
    description: "Description of the method type"
    name: "Display name"
    slug: "kebab-case-slug"
    methodDescription: "Additional description" (optional)
    examples:
      - "example1"
      - "example2"
```

**Categories**:
- `TypeOfClass` - Normal, Extending, Annotated, Record, None
- `MemberType` - Instance, Static, Either, None
- `Waaa` - Primitive, Annotated primitive, Wrapper, Special, Builder

---

### 3. data/config-types.yml
**Purpose**: Defines configuration data type definitions.

**Structure**:
```yaml
<CONFIG_TYPE>:
  description: "Description of the config type"
  name: "Display name"
```

**Allowed Values**:
- `COLOR` → name: `"Color"`
- `HOTKEY` → name: `"Hotkey"`
- `DROPDOWN` → name: `"Dropdown"`
- `BUTTON` → name: `"Button"`
- `FILE` → name: `"File"`
- `PAIR` → name: `"Pair"`
- `REGISTRY_ENTRY` → name: `"Registry entry"`
- `IDENTIFIER` → name: `"Identifier"`
- `DATE_TIME` → name: `"Date Time"`

---

### 4. data/dependencies.yml
**Purpose**: Defines library dependencies.

**Structure**:
```yaml
<DEPENDENCY_ID>:
  name: "Display name"
  url: "https://..."
```

**Allowed Values**:
- `FABRIC_API`
- `FABRIC_LANGUAGE_KOTLIN`
- `MALILIB`
- `UI_LIB`
- `MOD_MENU`
- `YET_ANOTHER_CONFIG_LIB`
- `PLACEHOLDER_API`

---

### 5. data/features.yml
**Purpose**: Defines configuration library features.

**Structure**:
```yaml
<FEATURE_NAME>:
  description: "Description of the feature"
  name: "Display name"
```

**Allowed Values**:
- `SLIDER` → name: `"Slider"`
- `CONSTRAINT` → name: `"Constraint"`
- `MOD_MENU_INTEGRATION` → name: `"Mod menu integration"`
- `CUSTOM_CONFIG_TYPES` → name: `"Custom config types"`
- `CODEC_BASED_CONFIGS` → name: `"Codec based configs"`
- `SECTIONS` → name: `"Config section"`
- `AUTO_UPGRADING` → name: `"Automatic upgrading"`

---

### 6. data/init-modes.yml
**Purpose**: Defines initialization mode for configurations.

**Structure**:
```yaml
<INIT_MODE>:
  description: "Description of the init mode"
```

**Allowed Values**:
- `YES` - Config values are initialized when created
- `AT_MOD_INIT` - Config values are initialized at mod initialization time
- `OPTIONAL` - Config initialization is optional
- `NO` - Config values are not initialized
- `NOT_AVAILABLE` - Config initialization is not available
- `UNKNOWN` - Initialization mode is unknown

---

### 7. data/sides.yml
**Purpose**: Defines configuration side types (client/server/both/unknown).

**Structure**:
```yaml
<SIDE_NAME>:
  description: "Description of the side"
  client: true/false
  server: true/false
```

**Allowed Values**:
- `CLIENT` → client: true, server: false
- `SERVER` → client: false, server: true
- `BOTH` → client: true, server: true
- `UNKNOWN` → client: false, server: false

---

### 8. data/types.yml
**Purpose**: Defines configuration type classifications (loader/ui/both/unknown).

**Structure**:
```yaml
<TYPE_NAME>:
  description: "Description of the type"
  loader: true/false
  ui: true/false
```

**Allowed Values**:
- `LOADER` → loader: true, ui: false
- `UI` → loader: false, ui: true
- `BOTH` → loader: true, ui: true
- `UNKNOWN` → loader: false, ui: false

---

### 9. data/ui-methods.yml
**Purpose**: Defines UI method for configuration screens.

**Structure**:
```yaml
<UI_METHOD>:
  description: "Description of the UI method"
  name: "Display name"
```

**Allowed Values**:
- `BUILDER` → name: `"Builder"`
- `AUTOMATIC` → name: `"Automatic"`
- `MANUAL` → name: `"Manual"`
- `COMMANDS` → name: `"Commands"`
- `NONE` → name: `"None"`

---

### 10. data/versions.yml
**Purpose**: Contains version information (computed dynamically).

**Structure**:
```yaml
---
# YAML frontmatter with library metadata
version: "1.0.0"
description: "Fabric API version information (computed dynamically)"
created: "YYYY-MM-DD"
---

# Fabric API version information
# These are computed dynamically from piston-meta API at runtime in build.js
```

**Note**: This file is empty of actual version data; versions are computed at build time.

---

## Library Definition Files (data/libraries/*.yml)

These files define individual configuration libraries.

### Common Frontmatter
All library files share this structure:

```yaml
---
# YAML frontmatter with library metadata
version: '1.0.0'
description: '<LibraryName> configuration library'
created: 'YYYY-MM-DD'
---
```

### Library Structure

```yaml
# Library definition

id: <library-id>
name: <Display Name>
side: <CLIENT|SERVER|BOTH>
modrinthSlug: <modrinth-slug>  # optional
type: <LOADER|UI|BOTH>
versions:
  - '1.19'
  - '1.20.1'
  - 'ALL_LIST'  # special value for all versions
dependencies:
  - name: <DEPENDENCY_ID>
    url: https://...
extraConfigTypes:
  - name: <CONFIG_TYPE>
    description: "Description"
extraFeatures:
  - <FEATURE_NAME>
  - UNKNOWN  # special value
configFormats:
  - <CONFIG_FORMAT>
  - UNKNOWN  # special value
manualInitialization: <YES|AT_MOD_INIT|OPTIONAL|NO|NOT_AVAILABLE>
configMethod:
  typeOfClass: <NORMAL|EXTENDING|ANNOTATED|RECORD|NONE|NOT_AVAILABLE>
  memberType: <INSTANCE|STATIC|EITHER|NONE|NOT_AVAILABLE>
  waaas:
    - name: <METHOD_NAME>
      methodDescription: "Description"
      examples:
        - "example"
uiMethod: <BUILDER|AUTOMATIC|MANUAL|COMMANDS|NONE|CUSTOM_SCREEN_CLASS>
notes:
  - "Note 1"
  - "Note 2"
source: https://...
exampleConfigClass: |
  <Java code example>
```

### Field Descriptions

| Field                  | Type   | Required | Description                                                            |
| ---------------------- | ------ | -------- | ---------------------------------------------------------------------- |
| `id`                   | string | Yes      | Unique library identifier (lowercase, hyphens allowed)                 |
| `name`                 | string | Yes      | Display name of the library                                            |
| `side`                 | string | Yes      | `CLIENT`, `SERVER`, or `BOTH`                                          |
| `modrinthSlug`         | string | No       | Modrinth slug for the library                                          |
| `type`                 | string | Yes      | `LOADER`, `UI`, or `BOTH`                                              |
| `versions`             | array  | No       | List of supported Minecraft versions. Use `ALL_LIST` for all versions. |
| `dependencies`         | array  | No       | List of required dependencies                                          |
| `extraConfigTypes`     | array  | No       | Extra config types supported                                           |
| `extraFeatures`        | array  | No       | Extra features supported                                               |
| `configFormats`        | array  | No       | Supported config file formats                                          |
| `manualInitialization` | string | Yes      | How config initialization works                                        |
| `configMethod`         | object | Yes      | How config methods are defined                                         |
| `uiMethod`             | string | Yes      | How UI is created                                                      |
| `notes`                | array  | No       | Additional notes about the library                                     |
| `source`               | string | Yes      | URL to the library's source code                                       |
| `exampleConfigClass`   | string | Yes      | Java code example (YAML multiline string)                              |

### Example Library Entry

```yaml
---
# YAML frontmatter with library metadata
version: '1.0.0'
description: 'Gson configuration library'
created: '2026-06-19'
---
# Gson library definition

id: gson
name: Gson
side: BOTH
versions: ALL_LIST
type: LOADER
dependencies: []
extraConfigTypes: []
extraFeatures: []
configFormats:
  - JSON
manualInitialization: YES
configMethod:
  typeOfClass: NORMAL
  memberType: INSTANCE
  waaas:
    - name: Primitive
      methodDescription: 'of primitive types'
      examples:
        - bool
        - int
        - String
uiMethod: NONE
notes:
  - 'Not really a config library, but still useful for some'
source: https://github.com/google/gson
exampleConfigClass: |
  public class ConfigClass {
    public boolean exampleBoolean = false;

    // could be anywhere
    public static ConfigClass config = new ConfigClass();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("gson-example");
    public static save() {
      try {
        Files.writeString(PATH, GSON.toJson(CONFIG));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    public static load() {
      try {
        CONFIG = GSON.fromJson(Files.readString(PATH));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
```

---

## Summary

| File Type | Location               | Count | Purpose                                      |
| --------- | ---------------------- | ----- | -------------------------------------------- |
| Metadata  | `data/*.yml`           | 11    | Define enumeration values and reference data |
| Library   | `data/libraries/*.yml` | 25    | Define individual config libraries           |

**Total YAML files**: 36
