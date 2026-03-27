package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class Configuration extends ConfigLibrary {
    public Configuration() {
        id = "configuration";
        name = "Configuration";
        side = Side.BOTH;
        versions = Versions.versions("1.16.5", "1.18.2", "1.19.2", "1.19.3", "1.19.4", "1.20.1", Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of();
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.CUSTOM_CONFIG_TYPES, Feature.CONSTRAINT, Feature.MOD_MENU_INTEGRATION, Feature.SECTIONS);
        configFormats = List.of(ConfigFormat.JSON, ConfigFormat.YAML, ConfigFormat.PROPERTIES, ConfigFormat.INI);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.ANNOTATED, true, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
        uiMethod = UiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/Toma1O6/Configuration";

		exampleConfigClass = """
@Config(id = "configuration-example")
public class ConfigClass {
	@Configurable
	public boolean exampleBoolean = false;
}
""";
    }
}
