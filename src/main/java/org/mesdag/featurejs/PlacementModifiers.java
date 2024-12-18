package org.mesdag.featurejs;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.*;

@SuppressWarnings("unused")
public class PlacementModifiers {
    public static final PlacementModifiers INSTANCE = new PlacementModifiers();

    public PlacementModifier biomeFilter() {
        return BiomeFilter.biome();
    }

    public PlacementModifier blockPredicateFilter(BlockPredicate predicate) {
        return BlockPredicateFilter.forPredicate(predicate);
    }

    public PlacementModifier carvingMask(GenerationStep.Carving pStep) {
        return CarvingMaskPlacement.forStep(pStep);
    }

    public PlacementModifier count(IntProvider pCount) {
        return CountPlacement.of(pCount);
    }

    public PlacementModifier count(int pCount) {
        return CountPlacement.of(pCount);
    }

    @Info(params = {
            @Param(name = "pDirectionOfSearch"),
            @Param(name = "pTargetCondition"),
            @Param(name = "pAllowedSearchCondition"),
            @Param(name = "pMaxSteps")
    })
    public PlacementModifier environmentScan(Direction pDirectionOfSearch, BlockPredicate pTargetCondition, BlockPredicate pAllowedSearchCondition, int pMaxSteps) {
        return EnvironmentScanPlacement.scanningFor(pDirectionOfSearch, pTargetCondition, pAllowedSearchCondition, pMaxSteps);
    }

    @Info(params = {
            @Param(name = "pDirectionOfSearch"),
            @Param(name = "pTargetCondition"),
            @Param(name = "pMaxSteps")
    })
    public PlacementModifier environmentScan(Direction pDirectionOfSearch, BlockPredicate pTargetCondition, int pMaxSteps) {
        return EnvironmentScanPlacement.scanningFor(pDirectionOfSearch, pTargetCondition, BlockPredicate.alwaysTrue(), pMaxSteps);
    }

    public PlacementModifier heightRange(HeightProvider pHeight) {
        return HeightRangePlacement.of(pHeight);
    }

    @Info(params = {
            @Param(name = "pMinInclusive"),
            @Param(name = "pMaxInclusive")
    })
    public PlacementModifier heightRangeUniform(VerticalAnchor pMinInclusive, VerticalAnchor pMaxInclusive) {
        return HeightRangePlacement.uniform(pMinInclusive, pMaxInclusive);
    }

    @Info(params = {
            @Param(name = "pMinInclusive"),
            @Param(name = "pMaxInclusive")
    })
    public PlacementModifier heightRangeTrapezoid(VerticalAnchor pMinInclusive, VerticalAnchor pMaxInclusive) {
        return HeightRangePlacement.triangle(pMinInclusive, pMaxInclusive);
    }

    public PlacementModifier heightmap(Heightmap.Types pHeightmap) {
        return HeightmapPlacement.onHeightmap(pHeightmap);
    }

    public PlacementModifier inSquare() {
        return InSquarePlacement.spread();
    }

    @Info(params = {
            @Param(name = "pNoiseToCountRatio"),
            @Param(name = "pNoiseFactor"),
            @Param(name = "pNoiseOffset")
    })
    public PlacementModifier noiseBasedCount(int pNoiseToCountRatio, double pNoiseFactor, double pNoiseOffset) {
        return NoiseBasedCountPlacement.of(pNoiseToCountRatio, pNoiseFactor, pNoiseOffset);
    }

    @Info(params = {
            @Param(name = "pNoiseLevel"),
            @Param(name = "pBelowNoise"),
            @Param(name = "pAboveNoise")
    })
    public PlacementModifier noiseThresholdCount(double pNoiseLevel, int pBelowNoise, int pAboveNoise) {
        return NoiseThresholdCountPlacement.of(pNoiseLevel, pBelowNoise, pAboveNoise);
    }

    @Info(params = {
            @Param(name = "pXSpread"),
            @Param(name = "pYSpread")
    })
    public PlacementModifier randomOffset(IntProvider pXSpread, IntProvider pYSpread) {
        return RandomOffsetPlacement.of(pXSpread, pYSpread);
    }

    @Info(params = {
            @Param(name = "pYSpread")
    })
    public PlacementModifier randomOffsetVertical(IntProvider pYSpread) {
        return RandomOffsetPlacement.vertical(pYSpread);
    }

    @Info(params = {
            @Param(name = "pXSpread")
    })
    public PlacementModifier randomOffsetHorizontal(IntProvider pXSpread) {
        return RandomOffsetPlacement.horizontal(pXSpread);
    }

    @Info(params = {
            @Param(name = "pChance")
    })
    public PlacementModifier rarityFiler(int pChance) {
        return RarityFilter.onAverageOnceEvery(pChance);
    }

    @Info(params = {
            @Param(name = "pHeightmap"),
            @Param(name = "pMinInclusive"),
            @Param(name = "pMaxInclusive")
    })
    public PlacementModifier surfaceRelativeThresholdFilter(Heightmap.Types pHeightmap, int pMinInclusive, int pMaxInclusive) {
        return SurfaceRelativeThresholdFilter.of(pHeightmap, pMinInclusive, pMaxInclusive);
    }

    public PlacementModifier surfaceWaterDepthFilter(int pMaxWaterDepth) {
        return SurfaceWaterDepthFilter.forMaxDepth(pMaxWaterDepth);
    }
}
