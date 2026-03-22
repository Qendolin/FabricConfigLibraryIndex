package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class Gson extends ConfigLibrary {
    public Gson() {
        id = "gson";
        name = "Gson";
        side = Side.BOTH;
        versions = Versions.ALL_LIST;
        type = Type.LOADER;
        dependencies = List.of();
		extraConfigTypes = ConfigType.UNKNOWN;
		extraFeatures = Feature.UNKNOWN;
        configFormats = List.of(ConfigFormat.JSON);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.NORMAL, true, ConfigMethod.Waaa.PRIMITIVE);
        guiMethod = GuiMethod.NONE;
        notes = "Not really a config library, but still useful for some";
        source = "https://github.com/google/gson";
    }
}
