package glowredman.darkerer.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

import glowredman.darkerer.DarkererConfig;
import glowredman.darkerer.DarkererCore;

@Mixin(World.class)
public class MixinWorld {

    @ModifyExpressionValue(
        at = @At(args = "floatValue=0.2", ordinal = 1, value = "CONSTANT"),
        expect = 1,
        method = "getSunBrightnessBody",
        remap = false)
    private float modifyMin(float original, @Share("min") LocalFloatRef min) {
        if (!DarkererCore.enabled) {
            return original;
        }
        switch (DarkererConfig.mode) {
            case EVERYWHERE:
                return 0.0f;
            case MOON_PHASE:
                return min.get();
            default:
                return original;
        }
    }

    @ModifyExpressionValue(
        at = @At(args = "floatValue=0.8", value = "CONSTANT"),
        expect = 1,
        method = "getSunBrightnessBody",
        remap = false)
    private float modifyMax(float original, @Share("min") LocalFloatRef min) {
        if (!DarkererCore.enabled) {
            return original;
        }
        switch (DarkererConfig.mode) {
            case EVERYWHERE:
                return 1.0f;
            case MOON_PHASE:
                int phase = (int) (Minecraft.getMinecraft().theWorld.getCurrentMoonPhaseFactorBody() * 10.0f);
                float max = 0.7f + (10 - phase) * 0.03f;
                min.set(1.0f - max);
                return max;
            default:
                return original;
        }
    }
}
