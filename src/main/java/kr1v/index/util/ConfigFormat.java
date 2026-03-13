package kr1v.index.util;

public enum ConfigFormat {
	JSON("Json"),
	TOML("Toml"),
	YAML("Yaml"),
	NOT_AVAILABLE("n/a"),
    UNKNOWN("?"),
	PROPERTIES(".properties"),
	INI(".ini"),
	JSON5("Json5"),
	HOCON("Hocon"),
	;

	public final String name;

	ConfigFormat(String name) {
		this.name = name;
	}
}
