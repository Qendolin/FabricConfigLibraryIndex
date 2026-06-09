package kr1v.index.libs;

import kr1v.index.util.*;

import java.util.List;

public class CodecConfigApi extends ConfigLibrary {
    public CodecConfigApi() {
        id = "codec-config-api";
        name = "Codec config API";
        side = Side.BOTH;
        versions = Versions.versions(Versions.MC_1_19_X, Versions.MC_1_20_X, Versions.MC_1_21_X, Versions.MC_26_X);
        type = Type.LOADER;
        dependencies = List.of();
        extraConfigTypes = ConfigType.UNKNOWN;
        extraFeatures = List.of(Feature.CODEC_BASED_CONFIGS, Feature.AUTO_UPGRADING);
        configFormats = List.of(ConfigFormat.JSON);
        manualInitialization = InitMode.YES;
        configMethod = ConfigMethod.of(ConfigMethod.TypeOfClass.EXTENDING, ConfigMethod.MemberType.INSTANCE, ConfigMethod.Waaa.PRIMITIVE);
        uiMethod = UiMethod.NONE;
        notes = List.of();
        source = "https://code.mschae23.de/mschae23/codec-config-api";

        exampleConfigClass = """
public record ExampleConfig(boolean exampleBoolean) implements ModConfig<ExampleConfig> {
    public static final MapCodec<ExampleConfig> TYPE_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.BOOL.fieldOf("example_boolean").forGetter(ExampleConfig::exampleBoolean)
    ).apply(instance, instance.stable(ExampleConfig::new)));

    public static final ModConfig.Type<ExampleConfig, ExampleConfig> TYPE = new ModConfig.Type<>(1, TYPE_CODEC);
    @SuppressWarnings("unchecked")
    public static final ModConfig.Type<ExampleConfig, ? extends ModConfig<ExampleConfig>>[] VERSIONS = new ModConfig.Type[] { TYPE, };
    public static final Codec<ModConfig<ExampleConfig>> CODEC = ModConfig.createCodec(TYPE.version(), version ->
        ModConfig.getConfigType(VERSIONS, version));

    public static final ExampleConfig DEFAULT = new ExampleConfig(true);

    @Override
    public Type<ExampleConfig, ?> type() {
        return TYPE;
    }

    @Override
    public ExampleConfig latest() {
        return this;
    }

    @Override
    public boolean shouldUpdate() {
        return true;
    }
}
""";
    }
}
