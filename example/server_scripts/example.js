FeatureEvents.configured(event => {
    event.create("featurejs:configured_stone_spiral").type("featurejs:stone_spiral").config(15);
})

FeatureEvents.placed(event => {
    let modifiers = event.getPlacementModifiers();
    event.create("featurejs:placed_stone_spiral")
        .feature("featurejs:configured_stone_spiral")
        .placement(
            modifiers.biomeFilter()
        );
})

FeatureEvents.biomeModifier(event => {
    event.addFeatures("#minecraft:is_overworld", "featurejs:placed_stone_spiral", 'surface_structures');
})

FeatureEvents.onPlace("featurejs:configured_stone_spiral", event => {
    let context = event.getContext();
    let topPos = context.level().getHeightmapPos('ocean_floor_wg', context.origin());
    let offset = Direction.NORTH;
    let height = context.config().getAsNumber(0);
    if (height < 0) {
        event.setSucceed(false);
    } else {
        for (let y = 0; y < height; y++) {
            offset = offset.getClockWise();
            context.level().setBlock(topPos.above(y).relative(offset), Blocks.STONE.defaultBlockState(), 3);
        }
    }
})
