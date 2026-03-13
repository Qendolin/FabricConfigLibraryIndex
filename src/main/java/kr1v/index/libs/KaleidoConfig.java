package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class KaleidoConfig extends ConfigLibrary {
	public KaleidoConfig() {
		id = "kaleido-config";
		name = "Kaleido config";
		side = Side.BOTH;
		versions = Versions.versions(Versions.ALL);
		type = Type.LOADER;
		dependencies = List.of();
		extraConfigTypes = List.of();
		extraFeatures = List.of();
		configFormat = ConfigFormat.TOML;
		manualInitialization = InitMode.YES;
		configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, true, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
		guiMethod = GuiMethod.NONE;
		notes = "Not made with minecraft in mind";
		source = "https://github.com/sisby-folk/kaleido-config";
	}
}
