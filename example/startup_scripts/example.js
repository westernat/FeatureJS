StartupEvents.registry("minecraft:worldgen/feature", event => {
    // If you have not set a codec for it, then you can only configure it in kubejs.
    // This situation also applies to the place method.
    event.create("featurejs:stone_spiral");

    event.create("featurejs:spiral")
        .codec( // The codec, allows you to use datapacks to configure it
            IntProvider.CODEC.fieldOf("height"),
            BlockStateProvider.CODEC.fieldOf("block")
        )
        .place(context => { // The place method, but not reloadable.
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

StartupEvents.registry("block", event => {
    event.create("featurejs:blend_oak_tree", "sapling")
        .basicFeature((random, hasFlower) => {
            if (random.nextInt(10) == 0) {
                return hasFlower ? "minecraft:fancy_oak_bees_005" : "minecraft:fancy_oak";
            } else {
                return hasFlower ? "minecraft:oak_bees_005" : "minecraft:oak";
            }
        })
        .megaFeature(random => "minecraft:dark_oak");
})
