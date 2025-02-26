package glowredman.darkerer.mixins;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import glowredman.darkerer.DarkererConfig;
import glowredman.darkerer.DarkererCore;

@Mixin(WorldProviderHell.class)
public abstract class MixinWorldProviderHell extends WorldProvider {

    @Inject(at = @At("HEAD"), cancellable = true, method = "generateLightBrightnessTable")
    private void replaceNetherLightCalc(CallbackInfo ci) {
        if (DarkererCore.enabled && DarkererConfig.darkNether) {
            super.generateLightBrightnessTable();
            ci.cancel();
        }
    }
}
