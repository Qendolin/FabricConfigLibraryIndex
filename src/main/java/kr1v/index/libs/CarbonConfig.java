package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class CarbonConfig extends ConfigLibrary {
	public CarbonConfig() {
		id = "carbon-config";
		name = "Carbon Config";
		side = Side.BOTH;
		modrinthSlug = "carbon-config";
		type = Type.BOTH;
		dependencies = List.of();
		extraConfigTypes = List.of();
		extraFeatures = List.of(Feature.CUSTOM_CONFIG_TYPES, Feature.MOD_MENU_INTEGRATION);
		configFormats = List.of(ConfigFormat.JSON);
		manualInitialization = InitMode.YES;
		configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, ConfigMethod.MemberType.INSTANCE, ConfigMethod.Waaa.builder("section.addBool()", "section.addInt()", "section.addString()"));
		uiMethod = UiMethod.NONE;
		notes = List.of();
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