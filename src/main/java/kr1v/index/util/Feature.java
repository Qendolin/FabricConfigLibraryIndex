package kr1v.index.util;

public enum Feature {
	SLIDER("Slider"),
	CONSTRAINT("Constraint", "Constraints for configs. For example, only allowing string configs that matches a certain regex"),
	MOD_MENU_INTEGRATION("Mod menu integration"),
	CUSTOM_CONFIG_TYPES("Custom config types", "Allows you to add your own config types"),
	;

	public final String name;
	public final String description;

	Feature(String name) {
		this(name, "");
	}

	Feature(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
