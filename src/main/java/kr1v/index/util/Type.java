package kr1v.index.util;

public enum Type {
	LOADER(true, false),
	GUI(false, true),
	BOTH(true, true),
	UNKNOWN(false, false),
	;

	public final boolean loader;
	public final boolean gui;

	Type(boolean loader, boolean gui) {
		this.loader = loader;
		this.gui = gui;
	}
}
