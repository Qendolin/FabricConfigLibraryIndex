package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class MonkeyLib538 extends ConfigLibrary {
	public MonkeyLib538() {
		id = "monkeylib538";
		name = "MonkeyLib538";
		side = Side.SERVER;
		modrinthSlug = "monkeylib538";
		type = Type.UI;
		dependencies = List.of(Dependency.FABRIC_API, Dependency.PLACEHOLDER_API);
		extraConfigTypes = List.of();
		extraFeatures = List.of();
		configFormats = List.of();
		manualInitialization = InitMode.YES;
		configMethod = ConfigMethod.NOT_AVAILABLE;
		uiMethod = UiMethod.COMMANDS;
		notes = List.of();
		source = "https://codeberg.org/OffsetMods538/MonkeyLib538";

		exampleConfigClass = "";
	}
}

