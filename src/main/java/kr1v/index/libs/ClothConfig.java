package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

import static kr1v.index.util.Versions.*;

public class ClothConfig extends ConfigLibrary {
	public ClothConfig() {
		id = "cloth-config";
		name = "Cloth config";
		side = Side.BOTH;
		versions = Versions.versions(MC_1_16_X, MC_1_17_X, MC_1_18_X, MC_1_19_X, MC_1_20_X, MC_1_21_X);
		type = Type.GUI;
		dependencies = List.of();
		extraConfigTypes = List.of(ConfigType.COLOR, ConfigType.DROPDOWN);
		extraFeatures = List.of(Feature.MOD_MENU_INTEGRATION);
		configFormats = List.of();
		manualInitialization = InitMode.NOT_AVAILABLE;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		guiMethod = GuiMethod.BUILDER;
		notes = "";
		source = "https://github.com/shedaniel/cloth-config";

		exampleConfigClass = "";
	}
}
