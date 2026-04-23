package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class McQoy extends ConfigLibrary {
    public McQoy() {
        id = "mcqoy";
        name = "McQoy";
        side = Side.CLIENT;
        versions = Versions.versions("1.16.5", Versions.MC_1_17_X, Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X, Versions.MC_26_X);
        type = Type.UI;
        dependencies = List.of(Dependency.MOD_MENU, Dependency.YET_ANOTHER_CONFIG_LIB);
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.MOD_MENU_INTEGRATION);
        configFormats = List.of();
        manualInitialization = InitMode.NOT_AVAILABLE;
        configMethod = ConfigMethod.NOT_AVAILABLE;
        uiMethod = UiMethod.AUTOMATIC;
        notes = "Makes a YACL config screen for kaleido configs";
        source = "https://github.com/sisby-folk/mcqoy";

		exampleConfigClass = "";
    }
}
