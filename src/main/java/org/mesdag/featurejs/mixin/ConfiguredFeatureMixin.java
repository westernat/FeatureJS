package org.mesdag.featurejs.mixin;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.mesdag.featurejs.FeatureJSPlugin;
import org.mesdag.featurejs.event.FeaturePlaceEventJS;
import org.mesdag.featurejs.feature.BasicFeatureJS;
import org.mesdag.featurejs.mixed.IConfiguredFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ConfiguredFeature.class)
public abstract class ConfiguredFeatureMixin<FC extends FeatureConfiguration, F extends Feature<FC>> implements IConfiguredFeature {
    @Shadow
    @Final
    private FC config;
    @Shadow
    @Final
    private F feature;
    @Unique
    private ResourceLocation featurejs$id;

    @HideFromJS
    @Override
    public void featurejs$setId(ResourceLocation id) {
        this.featurejs$id = id;
    }

    @Inject(method = "place", at = @At("HEAD"), cancellable = true)
    private void postEvent(WorldGenLevel pReader, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos, CallbackInfoReturnable<Boolean> cir) {
        if (featurejs$id != null && FeatureJSPlugin.ON_PLACE.hasListeners()) {
            if (pReader.ensureCanWrite(pPos)) {
                FeaturePlaceContext<BasicFeatureJS.Config> context = new FeaturePlaceContext<>(Optional.empty(), pReader, pChunkGenerator, pRandom, pPos, (BasicFeatureJS.Config) config);
                FeaturePlaceEventJS event = new FeaturePlaceEventJS(context, (BasicFeatureJS) feature);
                FeatureJSPlugin.ON_PLACE.post(event, featurejs$id);
                if (event.isUsed()) {
                    cir.setReturnValue(event.isSucceed());
                }
            } else {
                cir.setReturnValue(false);
            }
        }
    }
}
