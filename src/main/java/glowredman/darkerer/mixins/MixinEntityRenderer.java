package glowredman.darkerer.mixins;

import net.minecraft.client.renderer.EntityRenderer;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;

import glowredman.darkerer.DarkererConfig;
import glowredman.darkerer.DarkererCore;
import glowredman.darkerer.Mode;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Shadow
    private @Final int[] lightmapColors;

    @ModifyExpressionValue(
        at = { @At(args = "floatValue=0.03", value = "CONSTANT"), @At(args = "floatValue=0.05", value = "CONSTANT"),
            @At(args = "floatValue=0.35", value = "CONSTANT") },
        expect = 9,
        method = "updateLightmap")
    private float modifyMin(float original) {
        return DarkererCore.enabled ? 0.0f : original;
    }

    @ModifyExpressionValue(
        at = { @At(args = "floatValue=0.96", value = "CONSTANT"), @At(args = "floatValue=0.95", value = "CONSTANT"),
            @At(args = "floatValue=0.65", value = "CONSTANT") },
        expect = 9,
        method = "updateLightmap")
    private float modifyMax(float original) {
        return DarkererCore.enabled ? 1.0f : original;
    }

    @ModifyExpressionValue(
        at = @At(
            opcode = Opcodes.GETFIELD,
            target = "Lnet/minecraft/world/WorldProvider;dimensionId:I",
            value = "FIELD"),
        expect = 1,
        method = "updateLightmap")
    private int darkenEnd(int original) {
        return DarkererCore.enabled && DarkererConfig.darkEnd ? 0 : original;
    }

    @Inject(
        at = @At(
            opcode = Opcodes.GETFIELD,
            ordinal = 2,
            target = "Lnet/minecraft/client/renderer/EntityRenderer;mc:Lnet/minecraft/client/Minecraft;",
            value = "FIELD"),
        expect = 1,
        method = "updateLightmap")
    private void adjustNightVisionColor(CallbackInfo ci, @Local(ordinal = 8) LocalFloatRef r,
        @Local(ordinal = 9) LocalFloatRef g, @Local(ordinal = 10) LocalFloatRef b) {
        r.set(r.get() * 0.9f + 0.1f);
        g.set(g.get() * 0.9f + 0.1f);
        b.set(b.get() * 0.9f + 0.1f);
    }

    @Inject(
        at = @At(
            opcode = Opcodes.GETFIELD,
            target = "Lnet/minecraft/client/renderer/EntityRenderer;lightmapTexture:Lnet/minecraft/client/renderer/texture/DynamicTexture;",
            value = "FIELD"),
        expect = 1,
        method = "updateLightmap")
    private void modifyLightmap(CallbackInfo ci) {
        if (!DarkererCore.enabled || !DarkererConfig.removeBlueSkyLight || DarkererConfig.mode == Mode.EVERYWHERE) {
            return;
        }
        for (int i = 0; i < this.lightmapColors.length; i++) {
            int height = i / 16;
            if (height > 0 && height < 16) {
                int red = (this.lightmapColors[i] >> 16) & 0xFF;
                if (DarkererConfig.mode == Mode.ONLY_INSIDE) {
                    red = Math.min(0xFF, red + 25);
                }
                this.lightmapColors[i] = this.lightmapColors[i] & 0xFF000000 | red << 16 | red << 8 | red;
            }
        }
    }
}
