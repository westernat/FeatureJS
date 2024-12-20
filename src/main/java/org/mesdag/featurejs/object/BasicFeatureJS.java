package org.mesdag.featurejs.object;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.jetbrains.annotations.NotNull;
import org.mesdag.featurejs.codec.CodecBuilder;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgumentCountException;

import java.util.function.BiFunction;
import java.util.function.Predicate;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class BasicFeatureJS extends Feature<BasicFeatureJS.Config> {
    private final Builder builder;

    public BasicFeatureJS(Codec<Config> codec, Builder builder) {
        super(codec);
        this.builder = builder;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<Config> context) {
        return builder.placeFunction.apply(context, this);
    }

    @Override
    public void setBlock(@NotNull LevelWriter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        super.setBlock(level, pos, state);
    }

    @Override
    public void safeSetBlock(@NotNull WorldGenLevel level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Predicate<BlockState> predicate) {
        super.safeSetBlock(level, pos, state, predicate);
    }

    public static class Config implements FeatureConfiguration {
        @HideFromJS
        public Object[] arguments;

        public Config(Object... arguments) {
            if (arguments.length == 0) {
                throw new ArgumentCountException(0, 1, getClass().getName());
            }
            if (arguments.length > 16) {
                throw new ArgumentCountException(arguments.length, 16, getClass().getName());
            }
            this.arguments = arguments;
        }

        public Object get(int index) {
            return arguments[index];
        }

        public <T> T get(int index, Class<T> type) {
            return (T) get(index);
        }

        public Number getAsNumber(int index) {
            return (Number) get(index);
        }
    }

    public static class Builder extends BuilderBase<BasicFeatureJS> {
        private Codec<Config> codec;
        private BiFunction<FeaturePlaceContext<Config>, BasicFeatureJS, Boolean> placeFunction = (context, feature) -> false;

        public Builder(ResourceLocation i) {
            super(i);
        }

        public Builder codec(MapCodec<?>... mapCodecs) {
            if (mapCodecs.length == 0) {
                throw new RuntimeException("Codec count at least 1, but received 0");
            }
            CodecBuilder builder = new CodecBuilder();
            for (MapCodec<?> mapCodec : mapCodecs) {
                builder.argument(mapCodec);
            }
            this.codec = builder.build();
            return this;
        }

        public Builder place(BiFunction<FeaturePlaceContext<Config>, BasicFeatureJS, Boolean> function) {
            this.placeFunction = function;
            return this;
        }

        @Override
        public RegistryInfo<Feature> getRegistryType() {
            return RegistryInfo.FEATURE;
        }

        @Override
        public BasicFeatureJS createObject() {
            if (codec == null) {
                this.codec = Codec.unit(Config::new);
            }
            return new BasicFeatureJS(codec, this);
        }
    }
}
