package org.mesdag.featurejs.mixin;

import dev.latvian.mods.kubejs.core.MinecraftServerKJS;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import org.mesdag.featurejs.FeatureJSPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "reloadResources", at = @At("TAIL"))
    private void askUserAboutSomething(Collection<String> collection, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        if (FeatureJSPlugin.CONFIGURED.hasListeners() || FeatureJSPlugin.PLACED.hasListeners()) {
            ((MinecraftServerKJS) this).kjs$tell(Component.literal("FeatureJS: To apply reload for 'configured' or 'placed' event, you should exit your world and back again"));
        }
    }
}
