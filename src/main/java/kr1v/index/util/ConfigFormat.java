package kr1v.index.util;

import java.util.List;

public enum ConfigFormat {
	JSON("Json"),
	TOML("Toml"),
	YAML("Yaml"),
	NOT_AVAILABLE("n/a"),
	PROPERTIES(".properties"),
	INI(".ini"),
	JSON5("Json5"),
	HOCON("Hocon"),
	;

	public static final List<ConfigFormat> UNKNOWN = List.of();

	public final String name;

	ConfigFormat(String name) {
		this.name = name;
	}
}
