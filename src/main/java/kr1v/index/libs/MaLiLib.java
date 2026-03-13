package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class MaLiLib extends ConfigLibrary {
	public MaLiLib() {
		id = "malilib";
		name = "MaLiLib";
		side = Side.CLIENT;
		versions = Versions.versions(Versions.ALL);
		type = Type.BOTH;
		dependencies = List.of();
		extraConfigTypes = List.of(ConfigType.COLOR, ConfigType.HOTKEY);
		extraFeatures = List.of(Feature.SLIDER);
		configFormats = List.of(ConfigFormat.JSON);
		manualInitialization = InitMode.AT_MOD_INIT;
		configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, false, ConfigMethod.Waaa.SPECIAL);
		guiMethod = GuiMethod.CUSTOM_SCREEN_CLASS;
		notes = "Hard to set up";
		source = "https://github.com/sakura-ryoko/malilib";
	}
}
