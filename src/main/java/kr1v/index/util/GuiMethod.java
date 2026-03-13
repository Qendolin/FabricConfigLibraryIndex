package kr1v.index.util;

public enum GuiMethod {
	BUILDER("Builder", "Build a config screen using a builder class"),
	AUTOMATIC("Automatic", "You don't need to think about the config screen; it is done automatically"),
	CUSTOM_SCREEN_CLASS("Manual", "You build the entire config screen, with helpers"),
	NONE("None", "Guis are not officially supported"),
	;

	public final String name;
	public final String description;

	GuiMethod(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
