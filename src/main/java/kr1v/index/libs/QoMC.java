package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class QoMC extends ConfigLibrary {
	public QoMC() {
		id = "qomc";
		name = "QoMC";
		side = Side.SERVER;
		versions = Versions.versions(Versions.MC_1_16_X, Versions.MC_1_17_X, Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X, Versions.MC_26_X);
		type = Type.UI;
		dependencies = List.of(Dependency.FABRIC_API);
		extraConfigTypes = ConfigType.UNKNOWN;
		extraFeatures = Feature.UNKNOWN;
		configFormats = List.of();
		manualInitialization = InitMode.NO;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		uiMethod = UiMethod.COMMANDS;
		notes = "Makes config commands for kaleido configs";
		source = "https://github.com/AmberIsFrozen/qomc";

		exampleConfigClass = "";
	}
}
