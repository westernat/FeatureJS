package org.mesdag.featurejs;

import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

@SuppressWarnings({"unused"})
public class FeaturePlaceEventJS extends ServerEventJS {
    private final FeaturePlaceContext<BasicFeatureJS.Config> context;
    private final BasicFeatureJS feature;
    private boolean used = false;
    private boolean succeed = true;

    public FeaturePlaceEventJS(FeaturePlaceContext<BasicFeatureJS.Config> context, BasicFeatureJS feature) {
        super(context.level().getServer());
        this.context = context;
        this.feature = feature;
    }

    public FeaturePlaceContext<BasicFeatureJS.Config> getContext() {
        this.used = true;
        return context;
    }

    public BasicFeatureJS getFeature() {
        this.used = true;
        return feature;
    }

    public void setSucceed(boolean succeed) {
        if (!succeed) {
            this.used = true;
        }
        this.succeed = succeed;
    }

    @HideFromJS
    public boolean isUsed() {
        return used;
    }

    public boolean isSucceed() {
        return succeed;
    }
}
