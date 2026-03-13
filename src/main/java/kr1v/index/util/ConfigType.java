package kr1v.index.util;

public enum ConfigType {
	COLOR("Color"),
	HOTKEY("Hotkey"),
	DROPDOWN("Dropdown"),
	BUTTON("Button", "A button in the list of configs that does something when pressed"),
	FILE("File"),
	PAIR("Pair", "A pair of configs next to each other"),
	OBJECT("Object", "A \"Json object\" of configs"),
    REGISTRY_ENTRY("Registry entry"),
	IDENTIFIER("Identifier"),
	DATE_TIME("Date Time"),
	;

	public final String name;
	public final String description;

	ConfigType(String name) {
		this(name, "");
	}

	ConfigType(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
