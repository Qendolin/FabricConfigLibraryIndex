package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class ClothConfig extends ConfigLibrary {
	public ClothConfig() {
		id = "cloth-config";
		name = "Cloth config";
		side = Side.BOTH;
		modrinthSlug = "cloth-config";
		type = Type.UI;
		dependencies = List.of();
		extraConfigTypes = List.of(ConfigType.COLOR, ConfigType.DROPDOWN);
		extraFeatures = List.of(Feature.MOD_MENU_INTEGRATION);
		configFormats = List.of();
		manualInitialization = InitMode.NOT_AVAILABLE;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		uiMethod = UiMethod.BUILDER;
		notes = List.of();
		source = "https://github.com/shedaniel/cloth-config";

		exampleConfigClass = "";
	}
}
