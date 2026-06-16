package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class QoMC extends ConfigLibrary {
	public QoMC() {
		id = "qomc";
		name = "QoMC";
		side = Side.SERVER;
		modrinthSlug = "qomc";
		type = Type.UI;
		dependencies = List.of(Dependency.FABRIC_API);
		extraConfigTypes = ConfigType.UNKNOWN;
		extraFeatures = Feature.UNKNOWN;
		configFormats = List.of();
		manualInitialization = InitMode.NO;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		uiMethod = UiMethod.COMMANDS;
		notes = List.of("Makes config commands for kaleido configs");
		source = "https://github.com/AmberIsFrozen/qomc";

		exampleConfigClass = "";
	}
}
