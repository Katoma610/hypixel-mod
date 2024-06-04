package com.doranexius.hypixelmod.mixins;

import com.doranexius.hypixelmod.modules.render.ShowInvisibleEntities;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.entity.Entity.class)
public class MixinEntity {

    @Inject(method = "isInvisibleToPlayer", at = @At("HEAD"), cancellable = true)
    public void isInvisibleToPlayer(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (ShowInvisibleEntities.isToggled) {
            cir.setReturnValue(false);
        }
    }

}
