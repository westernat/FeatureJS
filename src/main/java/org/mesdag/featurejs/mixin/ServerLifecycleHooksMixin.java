package org.mesdag.featurejs.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.mesdag.featurejs.BiomeModifierEventJS;
import org.mesdag.featurejs.FeatureJSPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(value = ServerLifecycleHooks.class, remap = false)
public abstract class ServerLifecycleHooksMixin {
    @Shadow
    private static MinecraftServer currentServer;

    @ModifyVariable(method = "lambda$runModifiers$5", at = @At("HEAD"), argsOnly = true)
    private static List<BiomeModifier> biomeModifiers(List<BiomeModifier> value) {
        if (FeatureJSPlugin.BIOME_MODIFIERS.hasListeners()) {
            ImmutableList.Builder<BiomeModifier> builder = ImmutableList.builder();
            builder.addAll(value);
            BiomeModifierEventJS event = new BiomeModifierEventJS(currentServer);
            FeatureJSPlugin.BIOME_MODIFIERS.post(event);
            builder.addAll(event.getBiomeModifiers());
            return builder.build();
        }
        return value;
    }
}
