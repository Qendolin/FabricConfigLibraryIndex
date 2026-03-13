package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class ConfigToolKit extends ConfigLibrary {
    public ConfigToolKit() {
        id = "configtoolkit";
        name = "ConfigToolKit";
        side = Side.BOTH;
        versions = Versions.versions("1.20.6", Versions.MC_1_21_X);
        type = Type.LOADER;
        dependencies = List.of(Dependency.FABRIC_API);
        extraConfigTypes = null;
        extraFeatures = List.of(Feature.CODEC_BASED_CONFIGS);
        configFormats = List.of(ConfigFormat.JSON);
        manualInitialization = InitMode.AT_MOD_INIT;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.RECORD, true, ConfigMethod.Waaa.PRIMITIVE);
        guiMethod = GuiMethod.NONE;
        notes = "";
        source = "https://github.com/MattiDragon/ConfigToolkit";
    }
}
