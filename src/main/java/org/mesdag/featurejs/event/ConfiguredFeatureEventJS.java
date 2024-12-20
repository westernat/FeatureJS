package org.mesdag.featurejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.level.gen.properties.AddOreProperties;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.mesdag.featurejs.mixed.IConfiguredFeature;
import org.mesdag.featurejs.object.BasicFeatureJS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Info("To apply reload, you should exit your world and back again")
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
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

    public static class Builder<FC extends FeatureConfiguration, F extends Feature<FC>> {
        private final ResourceLocation id;
        private F feature;
        private FC config;

        public Builder(ResourceLocation id) {
            this.id = id;
        }

        public Builder type(ResourceLocation type) {
            this.feature = (F) RegistryInfo.FEATURE.getValue(type);
            if (feature == null) {
                throw new RuntimeException("Unknown feature type: '" + type + "' for '" + id + "'");
            }
            return this;
        }

        @Info("Only for basic features")
        public Builder config(Object... arguments) {
            this.config = (FC) new BasicFeatureJS.Config(arguments);
            return this;
        }

        @Info("Only for ore features")
        public Builder oreConfiguration(Consumer<AddOreProperties> consumer) {
            AddOreProperties properties = new AddOreProperties();
            consumer.accept(properties);
            this.config = (FC) new OreConfiguration(properties.targets, properties.size, properties.noSurface);
            return this;
        }

        @HideFromJS
        public ResourceLocation getId() {
            return id;
        }

        @HideFromJS
        public ConfiguredFeature<FC, F> getFeature() {
            ConfiguredFeature<FC, F> configuredFeature = new ConfiguredFeature<>(feature, config);
            ((IConfiguredFeature) (Record) configuredFeature).featurejs$setId(id);
            return configuredFeature;
        }
    }
}
