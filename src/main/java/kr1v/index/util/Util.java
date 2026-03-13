package kr1v.index.util;

import java.io.IOException;
import java.lang.reflect.Field;

public class Util {
	@SuppressWarnings("unchecked")
	public static <T> T get(Field f) {
		try {
			return (T) f.get(null);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Throwable> T rethrow(IOException e) throws T {
		throw (T) e;
	}
}
