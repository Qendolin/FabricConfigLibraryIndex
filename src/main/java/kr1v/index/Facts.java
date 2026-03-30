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

	public static final Fact day_8 = of(
			"30/03/2026",
"""
Only a couple libraries support all versions: Gson, Kaleido config, MaLiLib, MaLiLib API, and OffsetUtils538. Out of these, only MaLiLib and MaLiLib API are actual mods. Gson, Kaleido config and OffsetUtils538 are all pure java, and are thus automatically compatible with all minecraft versions (Given that they are compiled with the proper java version, of course).<br>
MaLiLib is compatible from 1.14 to 26.1 because of how important mods like tweakeroo and litematica are.<br>
MaLiLib API is compatible from 1.14 to 26.1 because it really does not interface with minecraft a lot; the only classes used are MinecraftClient (Yarn for the win!), Screen, Click, DrawContext, and MatrixStack. That's it! Only 5 classes!<br>
<br>
Bonus fact: You may ask, what is even the point of MaLiLib API if it doesn't interact with minecraft a lot? Well, as you can see in the index, MaLiLib requires an absurd amount of code to get started with, and its stupid "double declaration" of each config option is annoying too. MaLiLib API fixes all of these issues.<br>
Bonus bonus fact: That still doesn't answer why MaLiLib API is on 1.14 of all versions. Well, I first ported to 1.20.x, and thought, "Hmm... surely 1.19.x can't be that hard right?", and I was right! It only took a few days. This continued, all the way down to 1.14, at which point I stopped, because having 15 stonecutter branches was getting kind of ridiculous.<br>
Bonus bonus bonus fact: I wanted to port MaLiLib API (It is my mod) to other versions (1.12, 1.13), but due to 1.13 not having an official ornithe release, and due to 1.12 being in a massive rewrite, there was no point. Apart from this, I believe that MaLiLib API will become obsolete when Post Rewrite™ finally finishes, but since 2020 its to-do list has grown by more than 5 times, so for the foreseeable future I will continue to have a job.<br>
I could ramble on about MaLiLib API for a while longer, but that's enough bonus facts for one day.
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
