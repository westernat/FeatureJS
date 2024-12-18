StartupEvents.registry("minecraft:worldgen/feature", event => {
    event.create("featurejs:test").codec(
        Codec.INT.fieldOf("height")
    )
})
