package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class BetterConfig extends ConfigLibrary {
    public BetterConfig() {
        id = "better-config";
        name = "BetterConfig";
        side = Side.BOTH;
        versions = Versions.versions("1.19.3", "1.19.4", "1.20.0", "1.20.1", "1.21.0");
        type = Type.LOADER;
        dependencies = List.of();
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.CUSTOM_CONFIG_TYPES);
        configFormats = List.of();
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, false, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
        guiMethod = GuiMethod.NONE;
        notes = "Uses commands to modify configs";
        source = "https://github.com/xpple/BetterConfig/";
    }
}
