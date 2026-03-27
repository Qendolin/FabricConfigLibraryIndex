package kr1v.index.util;

import org.intellij.lang.annotations.Language;

import java.util.List;

public class ConfigLibrary {
	public String id;
	public String name;

	public Side side;

	public List<String> versions;
	public Type type;
	public List<Dependency> dependencies;
	public List<ConfigType> extraConfigTypes;
	public List<Feature> extraFeatures;
	public List<ConfigFormat> configFormats;
	public InitMode manualInitialization;
	public ConfigMethod configMethod;
	public UiMethod uiMethod;
	public String notes;
	public String source;

	@Language("java")
	public String exampleConfigClass;
}
