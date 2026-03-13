package kr1v.index;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import kr1v.index.libs.Libraries;
import kr1v.index.util.ConfigLibrary;
import kr1v.index.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	static void main() {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		Path dir = Path.of("json");
		try {
			Files.createDirectories(dir);

			JsonObject object = new JsonObject();

			for (ConfigLibrary library : Libraries.CONFIG_LIBRARIES()) {
				String fileName = library.id + ".json";

				object.add(library.id, gson.toJsonTree(library));

				Path path = dir.resolve(fileName);
				Files.writeString(path, gson.toJson(library));
			}

			Files.writeString(Path.of("libs.json"), gson.toJson(object));
		} catch (IOException e) {
			throw Util.rethrow(e);
		}
	}
}
