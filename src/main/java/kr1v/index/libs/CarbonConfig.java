package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class CarbonConfig extends ConfigLibrary {
	public CarbonConfig() {
		id = "carbon-config";
		name = "Carbon Config";
		side = Side.BOTH;
		versions = Versions.versions("1.16.5", "1.18.2", "1.19.2", "1.20.0", "1.20.1", "1.20.2", "1.20.4", "1.20.5", "1.20.6", "1.21.0", "1.21.1");
		type = Type.BOTH;
		dependencies = List.of();
		extraConfigTypes = List.of();
		extraFeatures = List.of(Feature.CUSTOM_CONFIG_TYPES, Feature.MOD_MENU_INTEGRATION);
		configFormats = List.of(ConfigFormat.JSON);
		manualInitialization = InitMode.YES;
		configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, true, ConfigMethod.Waaa.PRIMITIVE); // TODO: this is wrong actually. they use a builder
		uiMethod = UiMethod.NONE;
		notes = "";
		source = "https://github.com/Carbon-Config-Project/CarbonConfigLib";

		exampleConfigClass = """
public class ConfigClass {
	public static ConfigHandler CONFIG;
	public static ConfigSection SECTION;
	public static BoolValue EXAMPLE_BOOLEAN;

	static {
		Config config = new Config("example");
		ConfigSection section = config.add("general");
		EXAMPLE_BOOLEAN = section.addBool("example_key", false);
		CONFIG = CarbonConfig.CONFIGS.createConfig(config);
		CONFIG.register();
	}
}
""";
	}
}