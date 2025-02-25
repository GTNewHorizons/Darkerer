package glowredman.darkerer.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import glowredman.darkerer.Darkerer;
import glowredman.darkerer.DarkererConfig;
import twilightforest.world.WorldProviderTwilightForest;

@Mixin(WorldProviderTwilightForest.class)
public class MixinWorldProviderTwilightForest {

    @ModifyExpressionValue(at = @At(args = "floatValue=0.225", value = "CONSTANT"), method = "calculateCelestialAngle")
    private float modifyCelestialAngle(float original) {
        return Darkerer.enabled && DarkererConfig.twilightForest ? 0.5f : original;
    }
}
