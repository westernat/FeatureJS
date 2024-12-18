StartupEvents.registry("minecraft:worldgen/feature", event => {
    event.create("featurejs:stone_spiral").codec(Codec.INT.fieldOf("height"));

    event.create("featurejs:spiral")
        .codec(
            IntProvider.CODEC.fieldOf("height"),
            BlockStateProvider.CODEC.fieldOf("block")
        )
        .place(context => {
            let pos = context.origin();
            let config = context.config();
            let offset = Direction.NORTH;
            let height = config.get(0).sample(context.random());
            for (let y = 0; y < height; y++) {
                offset = offset.getClockWise();
                let blockPos = pos.above(y).relative(offset);
                context.level().setBlock(blockPos, config.get(1).getState(context.random(), blockPos), 3);
            }
            return true;
        });
})
