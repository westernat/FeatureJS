package org.mesdag.featurejs.feature;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ScatteredOreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

@SuppressWarnings("rawtypes")
public class ScatteredOreFeatureJS extends ScatteredOreFeature {
    public ScatteredOreFeatureJS(Codec<OreConfiguration> codec) {
        super(codec);
    }

    public static class Builder extends BuilderBase<ScatteredOreFeatureJS> {
        public Builder(ResourceLocation id) {
            super(id);
        }

        @Override
        public RegistryInfo getRegistryType() {
            return RegistryInfo.FEATURE;
        }

        @Override
        public ScatteredOreFeatureJS createObject() {
            return new ScatteredOreFeatureJS(OreConfiguration.CODEC);
        }
    }
}
