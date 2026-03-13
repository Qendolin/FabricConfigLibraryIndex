package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class MidnightLib extends ConfigLibrary {
    public MidnightLib() {
        id = "midnight-lib";
        name = "MidnightLib";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_17_X, Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of(Dependency.FABRIC_API);
        extraConfigTypes = List.of(ConfigType.FILE);
        extraFeatures = List.of(Feature.SLIDER, Feature.MOD_MENU_INTEGRATION);
        configFormat = ConfigFormat.UNKNOWN;
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, false, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
        guiMethod = GuiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/TeamMidnightDust/MidnightLib";
    }
}

