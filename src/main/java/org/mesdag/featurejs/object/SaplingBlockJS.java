package org.mesdag.featurejs.object;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
public class SaplingBlockJS extends SaplingBlock {
    public SaplingBlockJS(AbstractTreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    public static class Builder extends BlockBuilder {
        private BasicFeature basicFeature;
        private MegaFeature megaFeature;

        public Builder(ResourceLocation id) {
            super(id);
        }

        public Builder basicFeature(BasicFeature basicFeature) {
            this.basicFeature = basicFeature;
            return this;
        }

        public Builder megaFeature(MegaFeature megaFeature) {
            this.megaFeature = megaFeature;
            return this;
        }

        @Override
        public SaplingBlockJS createObject() {
            BasicFeature feature = Objects.requireNonNull(basicFeature);
            if (megaFeature == null) {
                return new SaplingBlockJS(new BasicTreeGrowerJS(feature), createProperties());
            }
            return new SaplingBlockJS(new MegaTreeGrowerJS(feature, megaFeature), createProperties());
        }
    }

    public static class BasicTreeGrowerJS extends AbstractTreeGrower {
        private final BasicFeature basicFeature;

        public BasicTreeGrowerJS(BasicFeature basicFeature) {
            this.basicFeature = basicFeature;
        }

        @Override
        protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean hasFlowers) {
            ResourceLocation feature = basicFeature.getConfiguredFeature(random, hasFlowers);
            if (feature == null) return null;
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, feature);
        }
    }

    public static class MegaTreeGrowerJS extends AbstractMegaTreeGrower {
        private final BasicFeature basicFeature;
        private final MegaFeature megaFeature;

        public MegaTreeGrowerJS(BasicFeature basicFeature, MegaFeature megaFeature) {
            this.basicFeature = basicFeature;
            this.megaFeature = megaFeature;
        }

        @Override
        protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(@NotNull RandomSource random) {
            ResourceLocation feature = megaFeature.getConfiguredMegaFeature(random);
            if (feature == null) return null;
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, feature);
        }

        @Override
        protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean hasFlowers) {
            ResourceLocation feature = basicFeature.getConfiguredFeature(random, hasFlowers);
            if (feature == null) return null;
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, feature);
        }
    }

    @FunctionalInterface
    public interface BasicFeature {
        @Nullable ResourceLocation getConfiguredFeature(RandomSource random, boolean hasFlowers);
    }

    @FunctionalInterface
    public interface MegaFeature {
        @Nullable ResourceLocation getConfiguredMegaFeature(RandomSource random);
    }
}
