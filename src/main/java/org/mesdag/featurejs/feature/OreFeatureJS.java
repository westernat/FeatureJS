package org.mesdag.featurejs.feature;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

@SuppressWarnings("rawtypes")
public class OreFeatureJS extends OreFeature {
    public OreFeatureJS(Codec<OreConfiguration> codec) {
        super(codec);
    }

    public static class Builder extends BuilderBase<OreFeatureJS> {
        public Builder(ResourceLocation id) {
            super(id);
        }

        @Override
        public RegistryInfo getRegistryType() {
            return RegistryInfo.FEATURE;
        }

        @Override
        public OreFeatureJS createObject() {
            return new OreFeatureJS(OreConfiguration.CODEC);
        }
    }
}
