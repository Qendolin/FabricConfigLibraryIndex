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
			"Creativity",
"""
<p>
Gson is the only config library that doesn't have "config" or "lib" in its name. be more creative!
</p>
"""
	);

	public static final Fact day_2 = of(
			"14/03/2026",
			"Language is hard",
"""
<p>
YAMLConfig doesn't mean "YAML (as in the file format) Config". it actually means "YAML (as in the file format) And More Languages Config"! it supports YAML, JSON5, TOML and HOCON, and is the only config library to support HOCON, as far as i am aware.
</p>
"""
	);

	public static final Fact day_3 = of(
			"21/03/2026",
			"File formats",
"""
<p>
There are a couple of file formats that only have one library supporting them: hocon (YAMLConfig, as seen in day two), .ini (Configuration), and .properties (Configuration). Bonus fact: if you check the index right now, you would see that the json5 ConfigFormat only has one usage. this is because when i made the first 8 or so entries to the index, i didn't know there was a difference in between json and json5, so only libraries added post-java-port are labeled as json5 correctly. and by chance, only one library added post-java-port supports json5.
</p>
"""
	);

	public static final Fact day_4 = of(
			"26/03/2026",
			"Client only configs",
"""
<p>
There are only 4 indexed config libraries that are marked as "client only". These are MaLiLib (because its developer is not interested in server-side), MaLiLib API (because it expands upon MaLiLib), McQoy (because its purpose is making a YACL gui for Kaleido configs automatically), and ukulib. Bonus fact: ukulib is the only library where its id matches its name! Bonus bonus fact: ukulib recently updated its readme to mention its [docs](https://uku3lig.github.io/ukulib/config/), which only concern 26.1. That means its in active development! yay!
</p>
"""
	);

	public static final Fact day_5 = of(
			"27/03/2026",
			"Configurable comments",
"""
<p>
Configurable is the only config library supporting javadocs for adding comments. for example:
</p>

<p>
Java:
<samp><pre class='codeSamp'>
/// Some Comment™
@Configurable
public static int someInt = 42;
</pre></samp>
</p>

<p>
Toml:
<samp><pre class='codeSamp'>
# Some Comment™
someInt = 42
</pre></samp>
</p>

<p>
Json:
<samp><pre class='codeSamp'>
{
  // Some Comment™
  someInt: 42
}
</pre></samp>
</p>

<p>
Despite Configurable being the only one supporting this awesome feature, numerous other config libraries have the technology to do this, off the top of my head owolib and malilib api, since they both use annotation processors, but I know there are more. Bonus fact: Configurable is the only config library not needing your config values to be bound to one or more specific classes. You can just put them anywhere in your codebase, and it'll work!
</p>
"""
	);

	public static final Fact day_6 = of(
			"28/03/2026",
			"Language is hard 2",
"""
<p>
Recently, I switched from saying "Gui" to "Ui" on the website and in the index. This is because numerous config libraries would provide command as a way to edit the config values in-game, and I would have to add a note to the config library stating that in fact this library does have a way to edit them in game. Now, there are 2 libraries doing this: MonkeyLib538 (Recent addition!) and BetterConfig. Bonus fact: MonkeyLib538 and its pure java JIJ dependency OffsetUtils538 are the only config libraries with numbers in their name! Bonus bonus fact: <code>oωo</code> is the only config library with a character in its name that isn't part of ASCII, making it the only config library whose name doesn't display correctly in the (or just mine) IntelliJ output, showing up as <code>o?o</code>. This isn't an issue on the website, because why would it be an issue in the first place.
</p>
"""
	);

	public static final Fact day_7 = of(
			"29/03/2026",
			"Server only configs",
"""
<p>
In day 4 I mentioned that only 4 indexed config libraries are marked as "client only". This seems like not a lot, but only two config libraries I know of would be marked as "server only"; QoMC and MonkeyLib538 (I know, both of these work in singleplayer too, but neither of them have any functionality for client side).<br>
QoMC is a config library designed to auto-generate commands based on existing Kaleido Config configs. Just like McQoy, Integration is implicit; you don't need to do anything to get it to work.<br>
MonkeyLib538 (Mentioned in day 6) does a similar thing, except works with OffsetUtils538 instead of Kaleido Config, though unlike QoMC does need manual initialization.
</p>

<p>
Bonus fact: The only pure-java config libraries are OffsetUtils538, Kaleido Config, and Gson!<br>
Bonus bonus fact: The only config libraries that provide a ui and are server-only are MonkeyLib538 and QoMC, because why would you write a config library that is server-only, but doesn't have a ui. Guess you'd make a library to mixin into options.txt :clueless:
</p>
"""
	);

	public static final Fact day_8 = of(
			"30/03/2026",
			"All versions",
"""
<p>
Only a couple libraries support all versions: Gson, Kaleido config, MaLiLib, MaLiLib API, and OffsetUtils538. Out of these, only MaLiLib and MaLiLib API are actual mods. Gson, Kaleido config and OffsetUtils538 are all pure java, and are thus automatically compatible with all minecraft versions (Given that they are compiled with the proper java version, of course).<br>
MaLiLib is compatible from 1.14 to 26.1 because of how important mods like tweakeroo and litematica are.<br>
MaLiLib API is compatible from 1.14 to 26.1 because it really does not interface with minecraft a lot; the only classes used are MinecraftClient (Yarn for the win!), Screen, Click, DrawContext, and MatrixStack. That's it! Only 5 classes!
</p>

<p>
Bonus fact: You may ask, what is even the point of MaLiLib API if it doesn't interact with minecraft a lot? Well, as you can see in the index, MaLiLib requires an absurd amount of code to get started with, and its stupid "double declaration" of each config option is annoying too. MaLiLib API fixes all of these issues.<br>
Bonus bonus fact: That still doesn't answer why MaLiLib API is on 1.14 of all versions. Well, I first ported to 1.20.x, and thought, "Hmm... surely 1.19.x can't be that hard right?", and I was right! It only took a few days. This continued, all the way down to 1.14, at which point I stopped, because having 15 stonecutter branches was getting kind of ridiculous.<br>
Bonus bonus bonus fact: I wanted to port MaLiLib API (It is my mod) to other versions (1.12, 1.13), but due to 1.13 not having an official ornithe release, and due to 1.12 being in a massive rewrite, there was no point. Apart from this, I believe that MaLiLib API will become obsolete when Post Rewrite™ finally finishes, but since 2020 its to-do list has grown by more than 5 times, so for the foreseeable future I will continue to have a job.<br>
I could ramble on about MaLiLib API for a while longer, but that's enough bonus facts for one day.
</p>
"""
	);

	public static final Fact day_9 = of(
			"31/03/2026",
			"MaLiLib API rambling",
"""
<p>
In day 8 I said that I could "ramble on about MaLiLib API for a while longer". Well, here goes.<br>
In day 8's bonus bonus fact I said that I had 15 stonecutter versions. That's actually a lie! I have 23 in the code, its just that some of them are unused: 1.19.3 and 1.19.2 (Test versions for some dumb issue), 1.18, 1.17, 1.14.4 and 1.14 (to check if my versions (like for example <code>>=1.14 <=1.15.2</code>) set actually work), and lastly 1.13.2 and 1.12.2 (Test ports to see if the grade setup is correct, et cetera)<br>
With that out of the way, I can begin to talk about the magic, and try to explain how it works (I tried a while ago, and failed miserably)<br>
At compile time, an annotation processor is used to remember the exact layout of a class (Specifically methods, fields and inner classes). This is done so I could have support for sections and <code>@Extras</code> annotations (I'll explain later what those are), and because reflection doesn't guarantee source order of fields, inner classes and methods. Not even the bytecode guarantees (And, as far as I've seen, this doesn't happen with any compiler) that the exact order/layout as defined in the source code is saved. I need this, because whatever order you put in your config class, that is the order that will show up in-game and in the file. The annotation processor writes the class layouts to <code>META-INF/kr1v/index.json</code> in your .jar file.<br>
Then, at runtime, first mod initialization runs. There, you can register your mod manually if you want a different name from mod id, or a custom config handler, or a custom input handler, or a custom config screen. Then, right after that, MaLiLib API reads all <code>META-INF/kr1v/index.json</code> files, reads each class, and handles them. To handle a class, it checks if there's a @Config annotation, and checks its mod id. If the mod id isn't registered yet, it registers the mod with a default config handler, input handler and config screen supplier. Then, it generates the list of configs, and registers a new tab to that mod id with the generated list of configs.<br>
To generate the list of configs, that's a whole different can of worms I will open right now:
</p>

<p>
Bonus fact 1: Due to a config entry only having to extend IConfigBase for it to get picked up, custom config types are implicitly supported by MaLiLib (And by extension MaLiLib API) (MaLiLib base requires you to provide a list of IConfigBases to use as the config entries for your config)<br>
</p>

<p>
First, it gets the class' layout from the <code>META-INF/kr1v/index.json</code> (Not really, it's a little more complicated, but this is already gonna be way too long, so whatever), and goes through every one of them. For each element, it handles the annotations (Regardless of type), then checks if its a field that is static and of type <code>IConfigBase</code> (Which is the interface that all configs should implement) (Doesn't have to be public! (Or final! I guess! That's a bug!)), and if so, adds it to the list. At the end, it returns the list.<br>
To handle the annotations, it goes through every annotation. For every annotation, it checks if the annotation is of type:<br>

<ul>
  <li><code>PopupConfig</code> (For sections): it... does a lot of validating, then generates a new list from the inner class, creates a ConfigObject (Which to be honest deserves its own entire day) (Really I should split this up into multiple days, but eh whatever no one reads these anyways) with a bunch of settings, registers it as a PopupConfig tab (Because otherwise it will not get saved) adds it to the list.</li>
  <li><code>Label</code> (For a label above a config entry): adds a new ConfigLabel with the <code>Label</code>s value</li>
  <li><code>Extras</code>: Here, it is assumed that the method annotated has a <code>List<IConfigBase></code> as its only argument. If one of its entrypoints (Its <code>runAt</code> argument) is an empty string, or if <code>runAt</code> is empty, it is invoked with the list as it is currently, to allow for programmatically adding/removing/whatever a config entry, or well, it allows you to do anything. It is your method, after all. What happens if <code>runAt</code> is not empty?</li>
  <li><code>Marker</code> (To mark a point in the list): It will go through every method in the class (From top to bottom), check if the method is annotated with <code>Extras</code>, and if so, checks if any of its <code>runAt</code> values match the value of the <code>Marker</code> annotation, and if so, runs the method with the current list again (I actually lie in the javadoc for <code>Extras</code> so its easier to make sense of).</li>
  <li><code>Hide</code>: Simply prevents the config entry from showing up in the gui, but it will still save.</li>
</ul>
If it is not any of these, simply ignores the annotation. In the future, I may add a way for developers to add their own annotation that they can process in the end.
</p>

<p>
And that's it! At least, for generating the config list.<br>
</p>

<p>
Then, when you open the config screen it grabs the list of visible configs (Accounting for <code>Hide</code> annotations and ConfigLabel config entries), and delegates (Most of) the rest to MaLiLib.
</p>

<p>
Bonus fact 2: in the "Vanilla" MaLiLib config screen, it uses the screen class for the mod switcher widget (>=1.21) for deciding which mod this config belongs to. Obviously this is very brittle, but okay. Whatever.<br>
Bonus fact 3: In the config screens render method, it has *eight* stonecutter branches, *just* so I could render a fancy background (And later for setting/saving the active tab and scroll wheel position). First, below 1.16, MatrixStack wasn't invented yet. Then, below 1.20.1, MatrixStack was invented. Then, in 1.20.1 and above, DrawContext was invented. In 1.20.1, <code>renderBackground()</code> did not have mouseX, mouseY and partialTicks as arguments. Then, from 1.20.2 to 1.20.5, aforementioned arguments were accounted for. Then, in 1.20.6 and above, it was renamed, and mouseX and mouseY were removed again. Lastly, in 1.21 to 1.21.1, applyBlur took in partialTicks, then in 1.21.3 to 1.21.5, <code>applyBlur()</code> did not take in any arguments. Then, finally, in 1.21.8 and above, drawContext was added as an argument.
</p>

<p>
I said "most of" for "the rest", so what is that "Most of", you may ask? Well, MaLiLib handles drawing the config list themselves (Not even completely, I'll elaborate in a second), rendering the config switcher, and... well... that's it actually. When I say that MaLiLib is a Pain in the Ass to set up, I mean it. There's a reason MaLiLib API exists.<br>
MaLiLib API makes a mixin into WidgetConfigOptionMixin to allow for custom config types.<br>
Bonus fact 4: MaLiLib APIs own new config types (ConfigList, ConfigButton, ConfigObject and ConfigPair) all use MaLiLib APIs way of registering custom config types (Well, actually widgets for custom config types), both to provide an example for other mods to do the same, and to make sure that my way of making custom config types actually *works*.
</p>

<p>
Bonus fact 5: Below (If I recall correctly) 1.19, ConfigBooleanHotkeyed displays as just a ConfigBoolean in the screen. This is not my fault, it is MaLiLibs fault. I could fix it, but decided against it. I could not be asked.
</p>

<p>
Wow. That took a while. 1 hour and 30 minutes. And I did not even cover half of the things I wanted to (I still wanted to cover ConfigObject, some of the struggles I've had developing this mod, some statistics about MaLiLib API, why I have 3 injects all responsible for "executing after mod initialization", and so much more), but I am running out of time (Who could have guessed, with 1.5 hours of writing in a row). I hope, to whoever read all of this, you learnt something, or at least had fun. I will probably not make another "Fact" *this* big, but who knows. I surprised myself too the second day in a row I managed to write up a config fact.<br>
</p>
"""
	);

	public static final Fact day_10 = of(
			"02/04/2026",
			"Dependencies",
"""
<p>
Fabric API is by far the most popular dependency, with 8 config libraries using it: ConfigToolKit, Configurable, Fzzy Config, MidnightLib, MonkeyLib538, oωo, QoMC, and YetAnotherConfigLib. The rest of the dependencies are one-offs: Fabric Language Kotlin (used by Fzzy Config), MaLiLib (used by MaLiLib API), UI Lib (used by YAML Config), Mod Menu (note: as a required dependency) (used by McQoy), YetAnotherConfigLib (used by McQoy), and lastly adventure-platform-mod (used by MonkeyLib538).
</p>
"""
	);

	public static final Fact day_11 = of(
			"03/04/2026",
			"Language problems",
"""
<p>
I switched to kotlin for generating the html at first just so I could do <code>${someVariable}</code>, but after a few minutes I discovered kotlinx html, so started using that instead.<br>
Bonus fact 1: I don't know any kotlin! I just googled some kotlin code, and just continued to do whatever it did. I don't know how those <code>{ it.someThing }</code> things work, or anything kotlin specific. Despite that, I've managed to write a total of 683 lines of kotlin code.<br>
Bonus fact 2: I don't know any html, css, or javascript either! I've just been kind of guessing, googling how to make text italic in html, and stuff like it. Literally just "throwing stuff at the wall and seeing what sticks".
</p>
"""
	);

	public static final Fact day_12 = of(
			"04/04/2026",
			"Versions",
"""
<p>
The most supported version is 1.21.0 (Note: <em>not</em> 1.21.1) with 23 usages. This is because BetterConfig, on Modrinth, does not support 1.21.1, and CompleteConfig is the only library to not support 1.21.0.<br>
After that, 1.21.1, with 22 libraries supporting it, and at third place a 7 way tie between 1.21.11, 1.21.9, 1.21.8, 1.21.7, 1.21.6, 1.21.5, and 1.20.1, each with 21 usages<br>
Then, 1.21.10, 1.21.4 and 1.21.3 have 20 usages (YAML Config does not support these, hence why they have one less supported lib than the other 1.21 versions)<br>
At 19 supported libs are 1.21.2 and 1.20.6 (Forge Config API Port does not support 1.21.2) (I couldn't figure out the sole library not supporting 1.20.6)<br>
I could continue, but I'll just put the entire list here (Note: I will assume that the 26.1 versions are off, because I don't know they are updated yet):<br>
</p>

<p>
<div class='panel' style='width: fit-content; margin: 2px;'>
<table style='font-size: 100%;'> <!--for some reason without this 100% it is bigger-->

<tr>
  <th>Versions</th>
  <th>Count</th>
</tr>

<tr><td>1.21.0</td>									<td style='text-align: center;'>23</td></tr>
<tr><td>1.21.1</td>									<td style='text-align: center;'>22</td></tr>
<tr><td>1.20.1, 1.21.5-1.21.9, 1.21.11</td>			<td style='text-align: center;'>21</td></tr>
<tr><td>1.21.3, 1.21.4, 1.21.10</td>				<td style='text-align: center;'>20</td></tr>
<tr><td>1.20.6, 1.21.2</td>							<td style='text-align: center;'>19</td></tr>
<tr><td>1.19.2-1.19.4, 1.20.0, 1.20.2, 1.20.4</td>	<td style='text-align: center;'>18</td></tr>
<tr><td>1.18.2, 1.19.0, 1.20.5</td>					<td style='text-align: center;'>16</td></tr>
<tr><td>1.19.1, 1.20.3</td>							<td style='text-align: center;'>15</td></tr>
<tr><td>1.18.0, 1.18.1</td>							<td style='text-align: center;'>13</td></tr>
<tr><td>1.16.5, 1.17.1</td>							<td style='text-align: center;'>12</td></tr>
<tr><td>1.17.0</td>									<td style='text-align: center;'>11</td></tr>
<tr><td>1.16.0-1.16.4</td>							<td style='text-align: center;'>8</td> </tr>
<tr><td>26.1.x</td>									<td style='text-align: center;'>7</td> </tr>
<tr><td>1.15.2</td>									<td style='text-align: center;'>6</td> </tr>
<tr><td>1.14.x, 1.15.0, 1.15.1</td>					<td style='text-align: center;'>5</td> </tr>

</table>
</div>
</p>
"""
	);

	public static final Fact day_13 = of(
			"05/04/2026",
			"Config types",
"""
<p>
A few config types have <em>more</em> than one supporting library: Color (Cloth config, MaLiLib, MaLiLib API, ukulib), Hotkey (MaLiLib, MaLiLib API), and Button (MaLiLib API, ukulib, YetAnotherConfigLib). (note: this does not include libraries that support codec based configs)<br>
The other ones are all one-offs:<br>
<ul>
	<li>Dropdown (Cloth config)</li>
	<li>File (MidnightLib)</li>
	<li>Pair (MaLiLib API)</li>
	<li>Registry entry (YAML Config)</li>
	<li>Identifier (YAML Config)</li>
	<li>Date Time (YAML Config)</li>
</ul>
</p>

<p>
Bonus fact: The config library supporting the most extra config types is MaLiLib API, with 4: Color, Hotkey, Pair and Button. After that, YAML Config at 3, and MaLiLib, Cloth config and ukulib all at 2.
</p>
"""
	);

	public static List<Fact> facts() {
		return Arrays.stream(Facts.class.getFields())
				.filter(f -> Fact.class.isAssignableFrom(f.getType()))
				.map(Util::<Fact>get)
				.toList();
	}
	private static Fact of(String date, String title, @Language("HTML") String fact) {
		return new Fact(LocalDate.parse(date, INPUT), title, fact);
	}

	public record Fact(LocalDate date, String title, String fact) {}
}
