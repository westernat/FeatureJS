package org.mesdag.featurejs;

import com.google.common.collect.Sets;
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
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.RemoveFeaturesBiomeModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class BiomeModifierEventJS extends ServerEventJS {
    private static final Set<Decoration> ALL_DECORATIONS = Sets.newHashSet(Decoration.values());
    private final List<BiomeModifier> biomeModifiers = new ArrayList<>();
    private final RegistryOps<JsonElement> registryOps;

    public BiomeModifierEventJS(MinecraftServer server) {
        super(server);
        this.registryOps = RegistryOps.create(JsonOps.INSTANCE, server.registryAccess());
    }

    @Info(params = {
            @Param(name = "biomes"),
            @Param(name = "features"),
            @Param(name = "step")
    })
    public void addFeatures(JsonElement biomesElement, JsonElement featuresElement, Decoration step) {
        HolderSet<Biome> biomes = Biome.LIST_CODEC.parse(registryOps, biomesElement).result().orElseThrow();
        HolderSet<PlacedFeature> features = PlacedFeature.LIST_CODEC.parse(registryOps, featuresElement).result().orElseThrow();
        biomeModifiers.add(new AddFeaturesBiomeModifier(biomes, features, step));
    }

    @Info(params = {
            @Param(name = "biomes"),
            @Param(name = "features"),
            @Param(name = "steps")
    }, value = "Remove features in giving steps")
    public void removeFeatures(JsonElement biomesElement, JsonElement featuresElement, Decoration... steps) {
        HolderSet<Biome> biomes = Biome.LIST_CODEC.parse(registryOps, biomesElement).result().orElseThrow();
        HolderSet<PlacedFeature> features = PlacedFeature.LIST_CODEC.parse(registryOps, featuresElement).result().orElseThrow();
        biomeModifiers.add(new RemoveFeaturesBiomeModifier(biomes, features, Sets.newHashSet(steps)));
    }

    @Info(params = {
            @Param(name = "biomes"),
            @Param(name = "features")
    }, value = "Remove features in all steps")
    public void removeFeatures(JsonElement biomesElement, JsonElement featuresElement) {
        HolderSet<Biome> biomes = Biome.LIST_CODEC.parse(registryOps, biomesElement).result().orElseThrow();
        HolderSet<PlacedFeature> features = PlacedFeature.LIST_CODEC.parse(registryOps, featuresElement).result().orElseThrow();
        biomeModifiers.add(new RemoveFeaturesBiomeModifier(biomes, features, ALL_DECORATIONS));
    }

    @HideFromJS
    public List<BiomeModifier> getBiomeModifiers() {
        return biomeModifiers;
    }
}
