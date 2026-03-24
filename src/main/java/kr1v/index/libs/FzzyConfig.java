package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class FzzyConfig extends ConfigLibrary {
    public FzzyConfig() {
        id = "fzzy-config";
        name = "Fzzy Config";
        side = Side.BOTH;
        versions = Versions.versions("1.20.1", "1.20.4", "1.20.5", "1.20.6", Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of(Dependency.FABRIC_API, Dependency.FABRIC_LANGUAGE_KOTLIN);
        extraConfigTypes = ConfigType.UNKNOWN;
        extraFeatures = List.of(Feature.SECTIONS);
        configFormats = List.of(ConfigFormat.JSON5, ConfigFormat.TOML, ConfigFormat.JSON, ConfigFormat.JSONC);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, true, List.of(ConfigMethod.Waaa.ANNOTATED_PRIMITIVE, ConfigMethod.Waaa.WRAPPER));
        guiMethod = GuiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/fzzyhmstrs/fconfig";

		exampleConfigClass = """
public class ConfigClass extends Config {
	public MyConfig() {
		super(Identifier.of("fzzy-config-example", "example"));
	}
	
    public boolean exampleBoolean = false;
}""";
    }
}
