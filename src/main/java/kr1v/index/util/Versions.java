package kr1v.index.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Versions {
	public static final List<String> ALL_LIST;
	public static final List<List<String>> ALL_SET;
	public static final List<String> ALL_MAJOR;

	static {
		String metaUrl = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json";
		Gson gson = new Gson();

		URI url = URI.create(metaUrl);
		JsonObject json;

		try (InputStreamReader reader = new InputStreamReader(
				url.toURL().openStream(), StandardCharsets.UTF_8)) {

			json = gson.fromJson(reader, JsonObject.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		JsonArray versions = json.get("versions").getAsJsonArray();

		List<String> allVersionsStrings = versions.asList().stream()
				.map(JsonElement::getAsJsonObject)
				.filter(version -> version.get("type").getAsString().equals("release"))
				.map(version -> version.get("id").getAsString())
				.toList()
				.reversed();

		ALL_LIST = allVersionsStrings
				.subList(55, allVersionsStrings.size())
				.reversed();

		List<List<String>> allSet = new ArrayList<>();

		for (String version : ALL_LIST.reversed()) {
			String[] parts = version.split("\\.");
			if (parts.length == 2) {
				allSet.add(new ArrayList<>());
			}
			allSet.getLast().add(version);
		}
		ALL_SET = allSet.reversed();
		ALL_MAJOR = ALL_SET.stream().map(List::getFirst).toList();
	}

	public static List<String> condensVersions(List<String> versions) {
		List<String> newVersions = new ArrayList<>();
		List<String> currentMajor = new ArrayList<>();
		String lastMajor = "0";
		String lastMinor = "0";
		for (String version : versions.reversed()) {
			String[] parts = version.split("\\.");
			if (!(lastMajor.equals(parts[0]) && lastMinor.equals(parts[1]))) {
				if (ALL_SET.contains(currentMajor)) {
					newVersions.add(currentMajor.getFirst() + ".x");
				} else {
					newVersions.addAll(currentMajor);
				}
				currentMajor = new ArrayList<>();
			}
			currentMajor.add(version);
			lastMajor = parts[0];
			lastMinor = parts[1];
		}
		if (!currentMajor.isEmpty()) {
			if (ALL_SET.contains(currentMajor)) {
				newVersions.add(currentMajor.getFirst() + ".x");
			} else {
				newVersions.addAll(currentMajor);
			}
		}
		return newVersions;
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
	public static List<String> versions(Object o) {
		List<String> list = new ArrayList<>();
		if (o instanceof String s) {
			list.add(s);
		} else if (o instanceof String[] strs) {
			list.addAll(Arrays.asList(strs));
		} else {
			throw new IllegalStateException(o.getClass() + "is not a String/String[]! " + o);
		}
		return list;
	}
}
