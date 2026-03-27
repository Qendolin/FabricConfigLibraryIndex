package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class OwoLib extends ConfigLibrary {
    public OwoLib() {
        id = "owo-lib";
        name = "oωo";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_17_X, Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of(Dependency.FABRIC_API);
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.CONSTRAINT);
        configFormats = ConfigFormat.UNKNOWN;
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.ANNOTATED, true, ConfigMethod.Waaa.PRIMITIVE);
        uiMethod = UiMethod.AUTOMATIC;
        notes = "Comes with a lot of additional, sometimes problematic, features.";
        source = "https://github.com/wisp-forest/owo-lib";

		exampleConfigClass = """
@Config(name = "owo-lib-example", wrapperName = "ConfigClass")
public class ConfigClassModel {
    public boolean exampleBoolean = false;
}
""";
    }
}
