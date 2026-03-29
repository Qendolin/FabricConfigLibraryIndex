package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class MonkeyLib538 extends ConfigLibrary {
	public MonkeyLib538() {
		id = "monkeylib538";
		name = "MonkeyLib538";
		side = Side.SERVER;
		versions = Versions.versions(Versions.MC_1_21_X);
		type = Type.UI;
		dependencies = List.of(Dependency.FABRIC_API, Dependency.ADVENTURE_PLATFORM_MOD);
		extraConfigTypes = List.of();
		extraFeatures = List.of();
		configFormats = List.of();
		manualInitialization = InitMode.YES;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		uiMethod = UiMethod.COMMANDS;
		notes = "";
		source = "https://codeberg.org/OffsetMods538/MonkeyLib538";

		exampleConfigClass = "";
	}
}

