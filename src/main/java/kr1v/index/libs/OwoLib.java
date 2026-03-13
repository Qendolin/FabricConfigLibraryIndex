package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class OwoLib extends ConfigLibrary {
    public OwoLib() {
        id = "oωo";
        name = "owo-lib";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_17_X, Versions.MC_1_18_X, Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X);
        type = Type.BOTH;
        dependencies = List.of(Dependency.FABRIC_API);
        extraConfigTypes = List.of();
        extraFeatures = List.of(Feature.CONSTRAINT);
        configFormat = ConfigFormat.UNKNOWN;
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.ANNOTATED, true, ConfigMethod.Waaa.PRIMITIVE);
        guiMethod = GuiMethod.AUTOMATIC;
        notes = "";
        source = "https://github.com/wisp-forest/owo-lib";
    }
}
