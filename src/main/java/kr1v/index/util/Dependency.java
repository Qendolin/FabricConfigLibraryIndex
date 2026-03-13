package kr1v.index.util;

public enum Dependency {
    FABRIC_API("Fabric API", "https://github.com/FabricMC/fabric-api"),
    FABRIC_LANGUAGE_KOTLIN("Fabric Language Kotlin", "https://github.com/FabricMC/fabric-language-kotlin"),
    MALILIB("MaLiLib", "https://github.com/sakura-ryoko/malilib"),
    UI_LIB("UI Lib", "https://github.com/DAQEM/UILib"),
    ;

    public final String name;
    public final String url;

    Dependency(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
