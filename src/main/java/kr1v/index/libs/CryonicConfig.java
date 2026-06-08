package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class CryonicConfig extends ConfigLibrary {
	public CryonicConfig() {
		id = "cryonic-config";
		name = "Cryonic Config";
		side = Side.BOTH;
		versions = Versions.versions(Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X);
		type = Type.LOADER;
		dependencies = List.of();
		extraConfigTypes = List.of();
		extraFeatures = List.of();
		configFormats = List.of(ConfigFormat.JSON);
		manualInitialization = InitMode.AT_MOD_INIT;
		configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, ConfigMethod.MemberType.INSTANCE, ConfigMethod.Waaa.PRIMITIVE);
		uiMethod = UiMethod.NONE;
		notes = List.of();
		source = "https://github.com/matthewperiut/cryonicconfig";

		exampleConfigClass = """
public class ConfigClass {
	static final ConfigStorage config = CryonicConfig.getConfig("mod_id");
	
	public static boolean getExampleBoolean() {
		config.getBoolean("exampleBoolean", false);
	}
}""";
	}
}
