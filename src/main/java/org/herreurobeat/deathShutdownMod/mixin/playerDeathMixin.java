package org.herreurobeat.deathShutdownMod.mixin;

import net.minecraft.network.packet.s2c.play.CombatEventS2CPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/**
 * Mixin for detecting death of client player. Credit: https://github.com/gliscowo/deathlog/blob/d8f7d354d9c51a72dd9aec33d2cf85e234c499a2/src/main/java/com/glisco/deathlog/mixin/ClientPlayNetworkHandlerMixin.java
 */
@Mixin(ClientPlayNetworkHandler.class)
public class playerDeathMixin {

    @Inject(method = "onCombatEvent", at = @At("HEAD"))
    public void onClientDeath(CombatEventS2CPacket packet, CallbackInfo ci) throws IOException {
        if (!RenderSystem.isOnRenderThread()) return;
        
    }
}
