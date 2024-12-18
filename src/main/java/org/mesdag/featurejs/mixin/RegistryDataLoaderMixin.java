package org.mesdag.featurejs.mixin;

import com.mojang.serialization.Decoder;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import org.mesdag.featurejs.ConfiguredFeatureEventJS;
import org.mesdag.featurejs.FeatureJSPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@SuppressWarnings("unchecked")
@Mixin(RegistryDataLoader.class)
public abstract class RegistryDataLoaderMixin {
    @Inject(method = "loadRegistryContents", at = @At("TAIL"))
    private static <E> void addConfiguredFeature(RegistryOps.RegistryInfoLookup pLookup, ResourceManager pManager, ResourceKey<? extends Registry<E>> pRegistryKey, WritableRegistry<E> pRegistry, Decoder<E> pDecoder, Map<ResourceKey<?>, Exception> pExceptions, CallbackInfo ci) {
        if (FeatureJSPlugin.CONFIGURED.hasListeners() && pRegistryKey.equals(Registries.CONFIGURED_FEATURE)) {
            ConfiguredFeatureEventJS event = new ConfiguredFeatureEventJS();
            FeatureJSPlugin.CONFIGURED.post(event);
            for (ConfiguredFeatureEventJS.Builder builder : event.getBuilders()) {
                ResourceKey<E> resourcekey = ResourceKey.create(pRegistryKey, builder.getId());
                pRegistry.register(resourcekey, (E) builder.getConfig(), Lifecycle.stable());
            }
        }
    }
}
