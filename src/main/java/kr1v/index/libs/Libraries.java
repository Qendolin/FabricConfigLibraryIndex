package kr1v.index.libs;

import kr1v.index.util.ConfigLibrary;
import kr1v.index.util.Util;

import java.util.Arrays;
import java.util.List;

public class Libraries {
	public static final ConfigLibrary BetterConfig = new BetterConfig();
	public static final ConfigLibrary CarbonConfig = new CarbonConfig();
	public static final ConfigLibrary ClothConfig = new ClothConfig();
	public static final ConfigLibrary CompleteConfig = new CompleteConfig();
	public static final ConfigLibrary ConfigToolKit = new ConfigToolKit();
	public static final ConfigLibrary Configurable = new Configurable();
	public static final ConfigLibrary Configuration = new Configuration();
	public static final ConfigLibrary CryonicConfig = new CryonicConfig();
	public static final ConfigLibrary ForgeConfigAPIPort = new ForgeConfigAPIPort();
	public static final ConfigLibrary FzzyConfig = new FzzyConfig();
	public static final ConfigLibrary Gson = new Gson();
	public static final ConfigLibrary KaleidoConfig = new KaleidoConfig();
	public static final ConfigLibrary MaLiLib = new MaLiLib();
	public static final ConfigLibrary MaLiLibAPI = new MaLiLibAPI();
	public static final ConfigLibrary McQoy = new McQoy();
	public static final ConfigLibrary MidnightLib = new MidnightLib();
	public static final ConfigLibrary MonkeyLib538 = new MonkeyLib538();
	public static final ConfigLibrary OffsetUtils538 = new OffsetUtils538();
	public static final ConfigLibrary OwoLib = new OwoLib();
	public static final ConfigLibrary QoMC = new QoMC();
	public static final ConfigLibrary Ukulib = new Ukulib();
	public static final ConfigLibrary YAMLConfig = new YAMLConfig();
	public static final ConfigLibrary YetAnotherConfigLib = new YetAnotherConfigLib();

	public static List<ConfigLibrary> CONFIG_LIBRARIES() {
		return Arrays.stream(Libraries.class.getFields()).filter(f -> f.getType().isAssignableFrom(ConfigLibrary.class)).map(Util::<ConfigLibrary>get).toList();
	}
}
