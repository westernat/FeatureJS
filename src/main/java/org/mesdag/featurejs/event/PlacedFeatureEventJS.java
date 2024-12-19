package org.mesdag.featurejs.event;

import com.google.common.collect.Lists;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.ArrayList;
import java.util.List;

@Info("To apply reload, you should exit your world and back again")
@SuppressWarnings("unused")
public class PlacedFeatureEventJS extends EventJS {
    private final List<Builder> builders = new ArrayList<>();
    private final MappedRegistry<ConfiguredFeature<?, ?>> registry;

    public PlacedFeatureEventJS(MappedRegistry<ConfiguredFeature<?, ?>> registry) {
        this.registry = registry;
    }

    public Builder create(ResourceLocation id) {
        Builder builder = new Builder(id, registry);
        builders.add(builder);
        return builder;
    }

    public PlacementModifiers getPlacementModifiers() {
        return PlacementModifiers.INSTANCE;
    }

    @HideFromJS
    public List<Builder> getBuilders() {
        return builders;
    }

    public static class Builder {
        private final ResourceLocation id;
        private final MappedRegistry<ConfiguredFeature<?, ?>> registry;
        private Holder<ConfiguredFeature<?, ?>> configuredFeature;
        private List<PlacementModifier> placement;

        public Builder(ResourceLocation id, MappedRegistry<ConfiguredFeature<?, ?>> registry) {
            this.id = id;
            this.registry = registry;
        }

        public Builder feature(ResourceLocation feature) {
            this.configuredFeature = registry.getHolderOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, feature));
            return this;
        }

        public Builder placement(PlacementModifier... modifiers) {
            this.placement = Lists.newArrayList(modifiers);
            return this;
        }

        @HideFromJS
        public ResourceLocation getId() {
            return id;
        }

        @HideFromJS
        public PlacedFeature getFeature() {
            return new PlacedFeature(configuredFeature, placement);
        }
    }
}
