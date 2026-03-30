package kr1v.index;

import kr1v.index.util.Util;
import org.intellij.lang.annotations.Language;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Facts {
	private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	public static final Fact day_1 = of(
			"13/03/2026",
"""
Gson is the only config library that doesn't have "config" or "lib" in its name. be more creative!
"""
	);

	public static final Fact day_2 = of(
			"14/03/2026",
"""
YAMLConfig doesn't mean "YAML (as in the file format) Config". it actually means "YAML (as in the file format) And More Languages Config"! it supports YAML, JSON5, TOML and HOCON, and is the only config library to support HOCON, as far as i am aware.
"""
	);

	public static final Fact day_3 = of(
			"21/03/2026",
"""
There are a couple of file formats that only have one library supporting them: hocon (YAMLConfig, as seen in day two), .ini (Configuration), and .properties (Configuration). Bonus fact: if you check the index right now, you would see that the json5 ConfigFormat only has one usage. this is because when i made the first 8 or so entries to the index, i didn't know there was a difference in between json and json5, so only libraries added post-java-port are labeled as json5 correctly. and by chance, only one library added post-java-port supports json5.
"""
	);

	public static final Fact day_4 = of(
			"26/03/2026",
"""
There are only 4 indexed config libraries that are marked as "client only". These are MaLiLib (because its developer is not interested in server-side), MaLiLib API (because it expands upon MaLiLib), McQoy (because its purpose is making a YACL gui for Kaleido configs automatically), and ukulib. Bonus fact: ukulib is the only library where its id matches its name! Bonus bonus fact: ukulib recently updated its readme to mention its [docs](https://uku3lig.github.io/ukulib/config/), which only concern 26.1. That means its in active development! yay!
"""
	);

	public static final Fact day_5 = of(
			"27/03/2026",
"""
Configurable is the only config library supporting javadocs for adding comments. for example:<br>
<br>
Java:
<samp><pre class='codeSamp'>
/// Some Comment™
@Configurable
public static int someInt = 42;
</pre></samp>
<br>
Toml:
<samp><pre class='codeSamp'>
# Some Comment™
someInt = 42
</pre></samp>
<br>
Json:
<samp><pre class='codeSamp'>
{
  // Some Comment™
  someInt: 42
}
</pre></samp><br>
Despite Configurable being the only one supporting this awesome feature, numerous other config libraries have the technology to do this, off the top of my head owolib and malilib api, since they both use annotation processors, but I know there are more. Bonus fact: Configurable is the only config library not needing your config values to be bound to one or more specific classes. You can just put them anywhere in your codebase, and it'll work!
"""
	);

	public static final Fact day_6 = of(
			"28/03/2026",
"""
Recently, I switched from saying "Gui" to "Ui" on the website and in the index. This is because numerous config libraries would provide command as a way to edit the config values in-game, and I would have to add a note to the config library stating that in fact this library does have a way to edit them in game. Now, there are 2 libraries doing this: MonkeyLib538 (Recent addition!) and BetterConfig. Bonus fact: MonkeyLib538 and its pure java JIJ dependency OffsetUtils538 are the only config libraries with numbers in their name! Bonus bonus fact: <code>oωo</code> is the only config library with a character in its name that isn't part of ASCII, making it the only config library whose name doesn't display correctly in the (or just mine) IntelliJ output, showing up as <code>o?o</code>. This isn't an issue on the website, because why would it be an issue in the first place.
"""
	);

	public static final Fact day_7 = of(
			"29/03/2026",
"""
In day 4 I mentioned that only 4 indexed config libraries are marked as "client only". This seems like not a lot, but only two config libraries I know of would be marked as "server only"; QoMC and MonkeyLib538 (I know, both of these work in singleplayer too, but neither of them have any functionality for client side).<br>
QoMC is a config library designed to auto-generate commands based on existing Kaleido Config configs. Just like McQoy, Integration is implicit; you don't need to do anything to get it to work.<br>
MonkeyLib538 (Mentioned in day 6) does a similar thing, except works with OffsetUtils538 instead of Kaleido Config, though unlike QoMC does need manual initialization.<br>
<br>
Bonus fact: The only pure-java config libraries are OffsetUtils538, Kaleido Config, and Gson!<br>
Bonus bonus fact: The only config libraries that provide a ui and are server-only are MonkeyLib538 and QoMC, because why would you write a config library that is server-only, but doesn't have a ui. Guess you'd make a library to mixin into options.txt :clueless:
"""
	);

	public static List<Fact> facts() {
		return Arrays.stream(Facts.class.getFields())
				.filter(f -> Fact.class.isAssignableFrom(f.getType()))
				.map(Util::<Fact>get)
				.toList();
	}
	private static Fact of(String date, @Language("HTML") String fact) {
		return new Fact(LocalDate.parse(date, INPUT), fact);
	}

	public record Fact(LocalDate date, String fact) {}
}
