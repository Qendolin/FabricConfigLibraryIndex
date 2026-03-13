package kr1v.index.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Versions {
	public static final String[] MC_1_14_X = ver("1.14.0", "1.14.1", "1.14.2", "1.14.3", "1.14.4");
	public static final String[] MC_1_15_X = ver("1.15.0", "1.15.1", "1.15.2");
	public static final String[] MC_1_16_X = ver("1.16.0", "1.16.1", "1.16.2", "1.16.3", "1.16.4", "1.16.5");
	public static final String[] MC_1_17_X = ver("1.17.0", "1.17.1");
	public static final String[] MC_1_18_X = ver("1.18.0", "1.18.1", "1.18.2");
	public static final String[] MC_1_19_X = ver("1.19.0", "1.19.1", "1.19.2", "1.19.3", "1.19.4");
	public static final String[] MC_1_20_X = ver("1.20.0", "1.20.1", "1.20.2", "1.20.3", "1.20.4", "1.20.5", "1.20.6");
	public static final String[] MC_1_21_X = ver("1.21.0", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6", "1.21.7", "1.21.8", "1.21.9", "1.21.10", "1.21.11");

	public static final String[] ALL = ver(MC_1_14_X, MC_1_15_X, MC_1_16_X, MC_1_17_X, MC_1_18_X, MC_1_19_X, MC_1_20_X, MC_1_21_X);

	public static String[] ver(String... strings) {
		return strings;
	}
	public static String[] ver(String[]... strings) {
		return Arrays.stream(strings).flatMap(Arrays::stream).toArray(String[]::new);
	}
	public static List<String> versions(Object... vers) {
		List<String> list = new ArrayList<>();
		for (Object o : vers) {
			if (o instanceof String s) {
				list.add(s);
			} else if (o instanceof String[] strs) {
				list.addAll(Arrays.asList(strs));
			} else {
				throw new IllegalStateException(o.getClass() + "is not a String/String[]! " + o);
			}
		}
		return list;
	}
}
