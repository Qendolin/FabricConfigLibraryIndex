package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class Ukulib extends ConfigLibrary {
    public Ukulib() {
        id = "ukulib";
        name = "ukulib";
        side = Side.CLIENT;
        versions = Versions.versions("1.15.2", "1.16.5", "1.17.1", "1.18.2", "1.19", "1.19.2", "1.19.3", "1.19.4", Versions.MC_1_20_X, Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of();
        extraConfigTypes = List.of(ConfigType.COLOR, ConfigType.BUTTON);
        extraFeatures = List.of(Feature.SLIDER);
        configFormats = List.of(ConfigFormat.JSON, ConfigFormat.TOML);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, true, ConfigMethod.Waaa.PRIMITIVE);
        guiMethod = GuiMethod.BUILDER;
        notes = "";
        source = "https://github.com/uku3lig/ukulib";
    }
}
