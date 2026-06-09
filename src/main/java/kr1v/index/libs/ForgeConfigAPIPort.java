package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class ForgeConfigAPIPort extends ConfigLibrary {
    public ForgeConfigAPIPort() {
        id = "forge-config-api-port";
        name = "Forge Config API Port";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_16_X, Versions.MC_1_18_X, Versions.MC_1_19_X, "1.20.0", "1.20.1", "1.20.2", "1.20.4", "1.20.6", "1.21.0", "1.21.1", "1.21.3", "1.21.4", "1.21.5", "1.21.6", "1.21.7", "1.21.8", "1.21.9", "1.21.10", "1.21.11");
        type = Type.LOADER;
        dependencies = List.of();
        extraConfigTypes = ConfigType.UNKNOWN;
        extraFeatures = Feature.UNKNOWN;
        configFormats = ConfigFormat.UNKNOWN;
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.UNKNOWN;
        uiMethod = UiMethod.NONE;
        notes = List.of();
        source = "https://github.com/Fuzss/forgeconfigapiport";

		// TODO: I tried for ~15 minutes, I can't find a coherent example, maybe later
    }
}
