FeatureEvents.configured(event => {
    // Creating configured_feature in kubejs directly.
    event.create("featurejs:configured_stone_spiral")
        .type("featurejs:stone_spiral")
        .config(15);
})

FeatureEvents.placed(event => {
    // Creating placed_feature in kubejs directly.
    // Whether you have a codec set up or not, you can create placed_feature in kubejs or datapacks.
    let modifiers = event.getPlacementModifiers();
    event.create("featurejs:placed_stone_spiral")
        .feature("featurejs:configured_stone_spiral")
        .placement(
            modifiers.biomeFilter()
        );
})

FeatureEvents.biomeModifier(event => {
    // Adding features for biomes in kubejs directly.
    // The same allows you to create this in kubejs or datapacks.
    event.addFeatures("#minecraft:is_overworld", "featurejs:placed_stone_spiral", 'surface_structures');
})

FeatureEvents.onPlace("featurejs:configured_stone_spiral", event => {
    // If you don't have a place method for the feature, then you can only placing here
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
