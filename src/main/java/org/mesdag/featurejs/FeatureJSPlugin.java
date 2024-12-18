package org.mesdag.featurejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.Extra;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;

public class FeatureJSPlugin extends KubeJSPlugin {
    public static final EventGroup GROUP = EventGroup.of("FeatureEvents");
    public static final EventHandler ON_PLACE = GROUP.server("onPlace", () -> FeaturePlaceEventJS.class).extra(Extra.ID);
    public static final EventHandler CONFIGURED = GROUP.server("configured", () -> ConfiguredFeatureEventJS.class);

    @Override
    public void init() {
        RegistryInfo.FEATURE.addType("basic", BasicFeatureJS.Builder.class, BasicFeatureJS.Builder::new);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("Codec", CodecWrapper.class);
        event.add("ExtraCodecs", ExtraCodecs.class);
        event.add("Feature", Feature.class);

        event.add("IntProvider", IntProvider.class);
        event.add("BiasedToBottomInt", BiasedToBottomInt.class);
        event.add("ClampedInt", ClampedInt.class);
        event.add("ClampedNormalInt", ClampedNormalInt.class);
        event.add("ConstantInt", ConstantInt.class);
        event.add("UniformInt", UniformInt.class);
        event.add("WeightedListInt", WeightedListInt.class);

        event.add("BlockStateProvider", BlockStateProvider.class);
        event.add("DualNoiseProvider", DualNoiseProvider.class);
        event.add("NoiseProvider", NoiseProvider.class);
        event.add("NoiseThresholdProvider", NoiseThresholdProvider.class);
        event.add("RandomizedIntStateProvider", RandomizedIntStateProvider.class);
        event.add("RotatedBlockProvider", RotatedBlockProvider.class);
        event.add("SimpleStateProvider", SimpleStateProvider.class);
        event.add("WeightedStateProvider", WeightedStateProvider.class);
    }

    @Override
    public void registerEvents() {
        GROUP.register();
    }
}
