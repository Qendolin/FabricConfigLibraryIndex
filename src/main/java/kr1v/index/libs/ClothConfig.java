package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class ClothConfig extends ConfigLibrary {
	public ClothConfig() {
		id = "cloth-config";
		name = "Cloth config";
		side = Side.BOTH;
		versions = List.of(
				"1.16.0", "1.16.1", "1.16.2", "1.16.3", "1.16.4", "1.16.5",
				"1.17.0", "1.17.1",
				"1.18.0", "1.18.1", "1.18.2",
				"1.19.0", "1.19.1", "1.19.2", "1.19.3", "1.19.4",
				"1.20.0", "1.20.1", "1.20.2", "1.20.3", "1.20.4", "1.20.5", "1.20.6",
				"1.21.0", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6", "1.21.7", "1.21.8", "1.21.9", "1.21.10", "1.21.11"
		);
		type = Type.GUI;
		dependencies = List.of();
		extraConfigTypes = List.of(ConfigType.COLOR, ConfigType.DROPDOWN);
		extraFeatures = List.of(Feature.MOD_MENU_INTEGRATION);
		configFormat = ConfigFormat.NOT_AVAILABLE;
		manualInitialization = InitMode.NOT_AVAILABLE;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		guiMethod = GuiMethod.BUILDER;
		notes = "";
		source = "https://github.com/shedaniel/cloth-config";
	}
}
