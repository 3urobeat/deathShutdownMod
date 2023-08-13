package org.x3urobeat.deathShutdownMod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
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

    @Inject(method = "onDeathMessage", at = @At("HEAD"))
    public void onClientDeath(DeathMessageS2CPacket packet, CallbackInfo ci) throws IOException {
        if (!RenderSystem.isOnRenderThread()) return;

        // Run shutdown method on death
        shutdownPC();
    }

    /**
     * Cross-platform shutdown function - Credit: https://stackoverflow.com/a/25666
     */
    private static void shutdownPC() throws RuntimeException, IOException {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name");

        if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
            shutdownCommand = "shutdown -h now";
        }
        else if ("Windows".equals(operatingSystem)) {
            shutdownCommand = "shutdown.exe -s -t 0";
        }
        else {
            throw new RuntimeException("Unsupported operating system.");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }
}
