package kr1v.index.util;

import java.util.List;

public class ConfigMethod {
	public final TypeOfClass typeOfClass;
	public final Boolean instance;
	public final List<Waaa> waaas;

	public static final ConfigMethod NOT_AVAILABLE = new ConfigMethod(null, null, null);
	public static final ConfigMethod UNKNOWN = new ConfigMethod(null, null, null);

	public ConfigMethod(TypeOfClass typeOfClass, Boolean instance, List<Waaa> waaas) {
		this.typeOfClass = typeOfClass;
		this.instance = instance;
		this.waaas = waaas;
	}

	public static ConfigMethod of(TypeOfClass typeOfClass, Boolean instance, List<Waaa> waaas) {
		return new ConfigMethod(typeOfClass, instance, waaas);
	}

	public static ConfigMethod of(TypeOfClass typeOfClass, Boolean instance, Waaa waaa) {
		return new ConfigMethod(typeOfClass, instance, List.of(waaa));
	}

	public enum Waaa {
		PRIMITIVE("Primitive", "int", "List<String>", "File"),
		ANNOTATED_PRIMITIVE("Annotated primitive", "@Config int", "@Comment(\"This is a string list\") List<String>", "@Config File"),
		WRAPPER("Wrapper", "Config<Integer>", "Config<List<String>>", "Config<File>"),
		SPECIAL("Special", "ConfigInteger", "ConfigList<ConfigString>", "FileConfig")
		;

		public final String name;
		public final String[] examples;

		Waaa(String name, String... examples) {
			this.name = name;
			this.examples = examples;
		}

		public String getExampleText() {
			String s = "Examples: ";
			for (String example : examples) {
				s += example + ", ";
			}
			s = s.substring(0, s.length() - 2);
			return s;
		}
	}

	public enum TypeOfClass {
		NORMAL("Normal"),
		EXTENDING("Extending"),
		ANNOTATED("Annotated"),
		RECORD("Record"),
		NONE("None"),
		;

		public final String name;

		TypeOfClass(String name) {
			this.name = name;
		}
	}
}
