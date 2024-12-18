package org.mesdag.featurejs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.rhino.util.HideFromJS;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgumentCountException;

@HideFromJS
@SuppressWarnings({"unchecked", "unused"})
public class CodecBuilder<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> {
    private MapCodec<T1> t1;
    private MapCodec<T2> t2;
    private MapCodec<T3> t3;
    private MapCodec<T4> t4;
    private MapCodec<T5> t5;
    private MapCodec<T6> t6;
    private MapCodec<T7> t7;
    private MapCodec<T8> t8;
    private MapCodec<T9> t9;
    private MapCodec<T10> t10;
    private MapCodec<T11> t11;
    private MapCodec<T12> t12;
    private MapCodec<T13> t13;
    private MapCodec<T14> t14;
    private MapCodec<T15> t15;
    private MapCodec<T16> t16;

    void argument(MapCodec<?> mapCodec) {
        if (t1 == null) {
            this.t1 = (MapCodec<T1>) mapCodec;
        } else if (t2 == null) {
            this.t2 = (MapCodec<T2>) mapCodec;
        } else if (t3 == null) {
            this.t3 = (MapCodec<T3>) mapCodec;
        } else if (t4 == null) {
            this.t4 = (MapCodec<T4>) mapCodec;
        } else if (t5 == null) {
            this.t5 = (MapCodec<T5>) mapCodec;
        } else if (t6 == null) {
            this.t6 = (MapCodec<T6>) mapCodec;
        } else if (t7 == null) {
            this.t7 = (MapCodec<T7>) mapCodec;
        } else if (t8 == null) {
            this.t8 = (MapCodec<T8>) mapCodec;
        } else if (t9 == null) {
            this.t9 = (MapCodec<T9>) mapCodec;
        } else if (t10 == null) {
            this.t10 = (MapCodec<T10>) mapCodec;
        } else if (t11 == null) {
            this.t11 = (MapCodec<T11>) mapCodec;
        } else if (t12 == null) {
            this.t12 = (MapCodec<T12>) mapCodec;
        } else if (t13 == null) {
            this.t13 = (MapCodec<T13>) mapCodec;
        } else if (t14 == null) {
            this.t14 = (MapCodec<T14>) mapCodec;
        } else if (t15 == null) {
            this.t15 = (MapCodec<T15>) mapCodec;
        } else if (t16 == null) {
            this.t16 = (MapCodec<T16>) mapCodec;
        } else {
            throw new ArgumentCountException(17, 16, BasicFeatureJS.Config.class.getName());
        }
    }

    Codec<BasicFeatureJS.Config> build() {
        if (t1 == null) {
            throw new NullPointerException();
        }
        if (t2 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t3 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t4 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t5 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t6 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t7 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t8 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t9 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t10 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t11 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t12 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9]),
                    t11.forGetter(config -> (T11) config.arguments[10])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t13 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9]),
                    t11.forGetter(config -> (T11) config.arguments[10]),
                    t12.forGetter(config -> (T12) config.arguments[11])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t14 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9]),
                    t11.forGetter(config -> (T11) config.arguments[10]),
                    t12.forGetter(config -> (T12) config.arguments[11]),
                    t13.forGetter(config -> (T13) config.arguments[12])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t15 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9]),
                    t11.forGetter(config -> (T11) config.arguments[10]),
                    t12.forGetter(config -> (T12) config.arguments[11]),
                    t13.forGetter(config -> (T13) config.arguments[12]),
                    t14.forGetter(config -> (T14) config.arguments[13])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        if (t16 == null) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    t1.forGetter(config -> (T1) config.arguments[0]),
                    t2.forGetter(config -> (T2) config.arguments[1]),
                    t3.forGetter(config -> (T3) config.arguments[2]),
                    t4.forGetter(config -> (T4) config.arguments[3]),
                    t5.forGetter(config -> (T5) config.arguments[4]),
                    t6.forGetter(config -> (T6) config.arguments[5]),
                    t7.forGetter(config -> (T7) config.arguments[6]),
                    t8.forGetter(config -> (T8) config.arguments[7]),
                    t9.forGetter(config -> (T9) config.arguments[8]),
                    t10.forGetter(config -> (T10) config.arguments[9]),
                    t11.forGetter(config -> (T11) config.arguments[10]),
                    t12.forGetter(config -> (T12) config.arguments[11]),
                    t13.forGetter(config -> (T13) config.arguments[12]),
                    t14.forGetter(config -> (T14) config.arguments[13]),
                    t15.forGetter(config -> (T15) config.arguments[14])
            ).apply(instance, BasicFeatureJS.Config::new));
        }
        return RecordCodecBuilder.create(instance -> instance.group(
                t1.forGetter(config -> (T1) config.arguments[0]),
                t2.forGetter(config -> (T2) config.arguments[1]),
                t3.forGetter(config -> (T3) config.arguments[2]),
                t4.forGetter(config -> (T4) config.arguments[3]),
                t5.forGetter(config -> (T5) config.arguments[4]),
                t6.forGetter(config -> (T6) config.arguments[5]),
                t7.forGetter(config -> (T7) config.arguments[6]),
                t8.forGetter(config -> (T8) config.arguments[7]),
                t9.forGetter(config -> (T9) config.arguments[8]),
                t10.forGetter(config -> (T10) config.arguments[9]),
                t11.forGetter(config -> (T11) config.arguments[10]),
                t12.forGetter(config -> (T12) config.arguments[11]),
                t13.forGetter(config -> (T13) config.arguments[12]),
                t14.forGetter(config -> (T14) config.arguments[13]),
                t15.forGetter(config -> (T15) config.arguments[14]),
                t16.forGetter(config -> (T16) config.arguments[15])
        ).apply(instance, BasicFeatureJS.Config::new));
    }
}
