package kr1v.index.util;

public enum InitMode {
	YES("Yes"),
	AT_MOD_INIT("At mod initialization"),
	OPTIONAL("Optional"),
	NO("No"),
	;

	public final String name;

	InitMode(String name) {
		this.name = name;
	}
}
