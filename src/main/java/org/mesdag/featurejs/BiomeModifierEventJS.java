package org.mesdag.featurejs;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BiomeModifierEventJS extends ServerEventJS {
    private final List<BiomeModifier> biomeModifiers = new ArrayList<>();

    public BiomeModifierEventJS(MinecraftServer server) {
        super(server);
    }

    @Info(params = {
            @Param(name = "biomes"),
            @Param(name = "features"),
            @Param(name = "decoration")
    })
    public void addFeatures(JsonElement biomesElement, JsonElement featuresElement, GenerationStep.Decoration decoration) {
        RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, server.registryAccess());
        HolderSet<Biome> biomes = Biome.LIST_CODEC.parse(registryOps, biomesElement).result().orElseThrow();
        HolderSet<PlacedFeature> features = PlacedFeature.LIST_CODEC.parse(registryOps, featuresElement).result().orElseThrow();
        biomeModifiers.add(new AddFeaturesBiomeModifier(biomes, features, decoration));
    }

    @HideFromJS
    public List<BiomeModifier> getBiomeModifiers() {
        return biomeModifiers;
    }
}
