package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class Configurable extends ConfigLibrary {
    public Configurable() {
        id = "configurable";
        name = "Configurable";
        side = Side.BOTH;
        versions = Versions.versions("1.20.1", Versions.MC_1_21_X);
        type = Type.LOADER;
        dependencies = List.of(Dependency.FABRIC_API);
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.CONSTRAINT, Feature.CUSTOM_CONFIG_TYPES);
        configFormats = List.of(ConfigFormat.JSON, ConfigFormat.TOML);
        manualInitialization = InitMode.NO;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NONE, false, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
        guiMethod = GuiMethod.NONE;
        notes = "";
        source = "https://github.com/Bawnorton/Configurable";

		exampleConfigClass = "/** Example comment */ @Configurable boolean exampleBoolean = false;";
    }
}
