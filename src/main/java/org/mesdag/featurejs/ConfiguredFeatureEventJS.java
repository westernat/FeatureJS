package org.mesdag.featurejs;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.mesdag.featurejs.mixed.IConfiguredFeature;

import java.util.ArrayList;
import java.util.List;

@Info("To apply reload, you should exit your world and back again")
@SuppressWarnings({"unused"})
public class ConfiguredFeatureEventJS extends EventJS {
    private final List<Builder> builders = new ArrayList<>();

    public Builder create(ResourceLocation id) {
        Builder builder = new Builder(id);
        builders.add(builder);
        return builder;
    }

    @HideFromJS
    public List<Builder> getBuilders() {
        return builders;
    }

    public static class Builder {
        private final ResourceLocation id;
        private BasicFeatureJS feature;
        private BasicFeatureJS.Config config;

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public Builder type(ResourceLocation type) {
            this.feature = (BasicFeatureJS) RegistryInfo.FEATURE.getValue(type);
            if (feature == null) {
                throw new RuntimeException("Unknown feature type: '" + type + "' for '" + id + "'");
            }
            return this;
        }

        public Builder config(Object... arguments) {
            int length = arguments.length;
            if (length == 0) throw new IllegalArgumentException("No arguments found!");
            this.config = new BasicFeatureJS.Config(arguments);
            return this;
        }

        @HideFromJS
        public ResourceLocation getId() {
            return id;
        }

        @HideFromJS
        public ConfiguredFeature<BasicFeatureJS.Config, BasicFeatureJS> getFeature() {
            ConfiguredFeature<BasicFeatureJS.Config, BasicFeatureJS> configuredFeature = new ConfiguredFeature<>(feature, config);
            ((IConfiguredFeature) (Record) configuredFeature).featurejs$setId(id);
            return configuredFeature;
        }
    }
}
