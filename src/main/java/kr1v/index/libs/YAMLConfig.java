package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class YAMLConfig extends ConfigLibrary {
    public YAMLConfig() {
        id = "yaml-config";
        name = "YAML Config";
        side = Side.BOTH;
        versions = Versions.versions("1.21.0", "1.21.1", "1.21.5", "1.21.6", "1.21.7", "1.21.8", "1.21.9", "1.21.11");
        type = Type.BOTH;
        dependencies = List.of(Dependency.UI_LIB);
        extraConfigTypes = List.of(ConfigType.IDENTIFIER, ConfigType.REGISTRY_ENTRY, ConfigType.DATE_TIME);
        extraFeatures = List.of(Feature.CUSTOM_CONFIG_TYPES, Feature.CONSTRAINT);
        configFormats = List.of(ConfigFormat.YAML, ConfigFormat.JSON5, ConfigFormat.TOML, ConfigFormat.HOCON);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, false, ConfigMethod.Waaa.SPECIAL); // TODO: I believe this is wrong, they use a builder
        guiMethod = GuiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/DAQEM/YamlConfig";

		// TODO: example config for this
		exampleConfigClass = "";
    }
}
