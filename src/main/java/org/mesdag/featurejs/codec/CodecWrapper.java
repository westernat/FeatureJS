package org.mesdag.featurejs.codec;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.SimpleMapCodec;
import com.mojang.serialization.codecs.UnboundedMapCodec;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface CodecWrapper {
    static <A> Codec<A> of(final Encoder<A> encoder, final Decoder<A> decoder) {
        return Codec.of(encoder, decoder);
    }

    static <A> Codec<A> of(final Encoder<A> encoder, final Decoder<A> decoder, final String name) {
        return Codec.of(encoder, decoder, name);
    }

    static <A> MapCodec<A> of(final MapEncoder<A> encoder, final MapDecoder<A> decoder) {
        return Codec.of(encoder, decoder);
    }

    static <A> MapCodec<A> of(final MapEncoder<A> encoder, final MapDecoder<A> decoder, final Supplier<String> name) {
        return Codec.of(encoder, decoder, name);
    }

    static <F, S> Codec<Pair<F, S>> pair(final Codec<F> first, final Codec<S> second) {
        return Codec.pair(first, second);
    }

    static <F, S> Codec<Either<F, S>> either(final Codec<F> first, final Codec<S> second) {
        return Codec.either(first, second);
    }

    static <F, S> MapCodec<Pair<F, S>> mapPair(final MapCodec<F> first, final MapCodec<S> second) {
        return Codec.mapPair(first, second);
    }

    static <F, S> MapCodec<Either<F, S>> mapEither(final MapCodec<F> first, final MapCodec<S> second) {
        return Codec.mapEither(first, second);
    }

    static <E> Codec<List<E>> list(final Codec<E> elementCodec) {
        return Codec.list(elementCodec);
    }

    static <K, V> Codec<List<Pair<K, V>>> compoundList(final Codec<K> keyCodec, final Codec<V> elementCodec) {
        return Codec.compoundList(keyCodec, elementCodec);
    }

    static <K, V> SimpleMapCodec<K, V> simpleMap(final Codec<K> keyCodec, final Codec<V> elementCodec, final Keyable keys) {
        return Codec.simpleMap(keyCodec, elementCodec, keys);
    }

    static <K, V> UnboundedMapCodec<K, V> unboundedMap(final Codec<K> keyCodec, final Codec<V> elementCodec) {
        return Codec.unboundedMap(keyCodec, elementCodec);
    }

    static <F> MapCodec<Optional<F>> optionalField(final String name, final Codec<F> elementCodec) {
        return Codec.optionalField(name, elementCodec);
    }

    static <A> Codec<A> unit(final A defaultValue) {
        return Codec.unit(defaultValue);
    }

    static <A> Codec<A> unit(final Supplier<A> defaultValue) {
        return Codec.unit(defaultValue);
    }

    static Codec<Integer> intRange(int minInclusive, int maxInclusive) {
        return Codec.intRange(minInclusive, maxInclusive);
    }

    static Codec<Float> floatRange(float minInclusive, float maxInclusive) {
        return Codec.floatRange(minInclusive, maxInclusive);
    }

    static Codec<Double> doubleRange(double minInclusive, double maxInclusive) {
        return Codec.doubleRange(minInclusive, maxInclusive);
    }

    PrimitiveCodec<Boolean> BOOL = Codec.BOOL;
    PrimitiveCodec<Byte> BYTE = Codec.BYTE;
    PrimitiveCodec<Short> SHORT = Codec.SHORT;
    PrimitiveCodec<Integer> INT = Codec.INT;
    PrimitiveCodec<Long> LONG = Codec.LONG;
    PrimitiveCodec<Float> FLOAT = Codec.FLOAT;
    PrimitiveCodec<Double> DOUBLE = Codec.DOUBLE;
    PrimitiveCodec<String> STRING = Codec.STRING;
}
