FeatureEvents.configured(event => {
    event.create('featurejs:normal_test').type('featurejs:test').config(15)
})

FeatureEvents.onPlace('featurejs:normal_test', event => {
    let context = event.getContext();
    let topPos = context.level().getHeightmapPos('ocean_floor_wg', context.origin());
    let offset = Direction.NORTH;
    let height = context.config().getAsNumber(0);
    for (let y = 0; y < height; y++) {
        offset = offset.getClockWise();
        context.level().setBlock(topPos.above(y).relative(offset), Blocks.STONE.defaultBlockState(), 3);
    }
})
