package org.mesdag.featurejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.util.ExtraCodecs;

public class FeatureJSPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryInfo.FEATURE.addType("basic", BasicFeatureJS.Builder.class, BasicFeatureJS.Builder::new);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("Codec", CodecWrapper.class);
        event.add("ExtraCodecs", ExtraCodecs.class);
    }
}
