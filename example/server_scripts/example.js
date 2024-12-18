FeatureEvents.configured(event => {
    event.create('featurejs:normal_stone_spiral').type('featurejs:stone_spiral').config(15)
})

FeatureEvents.onPlace('featurejs:normal_stone_spiral', event => {
    let context = event.getContext();
    let topPos = context.level().getHeightmapPos('ocean_floor_wg', context.origin());
    let offset = Direction.NORTH;
    let height = context.config().getAsNumber(0);
    if (height < 0) {
        event.setSucceed(false)
    } else {
        for (let y = 0; y < height; y++) {
            offset = offset.getClockWise();
            context.level().setBlock(topPos.above(y).relative(offset), Blocks.STONE.defaultBlockState(), 3);
        }
    }
})
