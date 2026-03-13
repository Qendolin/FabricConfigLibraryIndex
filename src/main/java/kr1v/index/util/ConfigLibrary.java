package kr1v.index.util;

import java.util.List;

public class ConfigLibrary {
	public String id;
	public String name;

	public Side side;

	public List<String> versions;
	public Type type;
	public List<String> dependencies;
	public List<ConfigType> extraConfigTypes;
	public List<Feature> extraFeatures;
	public ConfigFormat configFormat;
	public InitMode manualInitialization;
	public ConfigMethod configMethod;
	public GuiMethod guiMethod;
	public String notes;
	public String source;
}
