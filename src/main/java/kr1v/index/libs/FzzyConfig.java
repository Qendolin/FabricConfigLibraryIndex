package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class FzzyConfig extends ConfigLibrary {
    public FzzyConfig() {
        id = "fzzy-config";
        name = "Fzzy Config";
        side = Side.BOTH;
        versions = Versions.versions("1.20.1", "1.20.4", "1.20.5", "1.20.6", Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of(Dependency.FABRIC_API, Dependency.FABRIC_LANGUAGE_KOTLIN);
        extraConfigTypes = ConfigType.UNKNOWN;
        extraFeatures = Feature.UNKNOWN;
        configFormats = List.of(ConfigFormat.JSON, ConfigFormat.TOML);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, true, List.of(ConfigMethod.Waaa.ANNOTATED_PRIMITIVE, ConfigMethod.Waaa.WRAPPER));
        guiMethod = GuiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/fzzyhmstrs/fconfig";
    }
}
