package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class YetAnotherConfigLib extends ConfigLibrary {
    public YetAnotherConfigLib() {
        id = "yacl";
        name = "YetAnotherConfigLib";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of("Fabric API");
        extraConfigTypes = List.of(ConfigType.BUTTON);
        extraFeatures = List.of();
        configFormat = ConfigFormat.JSON;
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, true, ConfigMethod.Waaa.ANNOTATED_PRIMITIVE);
        guiMethod = GuiMethod.BUILDER;
        notes = "";
        source = "https://github.com/isXander/YetAnotherConfigLib";
    }
}
