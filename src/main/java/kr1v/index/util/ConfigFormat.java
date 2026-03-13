package kr1v.index.util;

public enum ConfigFormat {
	JSON("Json"),
	TOML("Toml"),
	YAML("Yaml"),
	;

	public final String name;

	ConfigFormat(String name) {
		this.name = name;
	}
}
