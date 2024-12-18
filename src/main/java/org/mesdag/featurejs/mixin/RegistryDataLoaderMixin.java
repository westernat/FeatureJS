package org.mesdag.featurejs.mixin;

import com.mojang.serialization.Decoder;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.mesdag.featurejs.ConfiguredFeatureEventJS;
import org.mesdag.featurejs.FeatureJSPlugin;
import org.mesdag.featurejs.PlacedFeatureEventJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@SuppressWarnings("unchecked")
@Mixin(RegistryDataLoader.class)
public abstract class RegistryDataLoaderMixin {
    @Unique
    private static WritableRegistry<?> featurejs$configuredFeatureRegistry;

    @Inject(method = "loadRegistryContents", at = @At("TAIL"))
    private static <E> void addConfiguredFeature(RegistryOps.RegistryInfoLookup pLookup, ResourceManager pManager, ResourceKey<? extends Registry<E>> pRegistryKey, WritableRegistry<E> pRegistry, Decoder<E> pDecoder, Map<ResourceKey<?>, Exception> pExceptions, CallbackInfo ci) {
        if (FeatureJSPlugin.CONFIGURED.hasListeners() && pRegistryKey.equals(Registries.CONFIGURED_FEATURE)) {
            featurejs$configuredFeatureRegistry = pRegistry;
            ConfiguredFeatureEventJS event = new ConfiguredFeatureEventJS();
            FeatureJSPlugin.CONFIGURED.post(event);
            for (ConfiguredFeatureEventJS.Builder builder : event.getBuilders()) {
                ResourceKey<E> resourcekey = ResourceKey.create(pRegistryKey, builder.getId());
                pRegistry.register(resourcekey, (E) builder.getFeature(), Lifecycle.stable());
            }
        } else if (FeatureJSPlugin.PLACED.hasListeners() && pRegistryKey.equals(Registries.PLACED_FEATURE)) {
            if (featurejs$configuredFeatureRegistry instanceof MappedRegistry<?> mappedRegistry) {
                PlacedFeatureEventJS event = new PlacedFeatureEventJS((MappedRegistry<ConfiguredFeature<?, ?>>) mappedRegistry);
                FeatureJSPlugin.PLACED.post(event);
                for (PlacedFeatureEventJS.Builder builder : event.getBuilders()) {
                    ResourceKey<E> resourcekey = ResourceKey.create(pRegistryKey, builder.getId());
                    pRegistry.register(resourcekey, (E) builder.getFeature(), Lifecycle.stable());
                }
                featurejs$configuredFeatureRegistry = null;
            }
        }
    }
}
