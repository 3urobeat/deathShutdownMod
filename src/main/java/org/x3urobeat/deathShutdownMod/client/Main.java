package org.x3urobeat.deathShutdownMod.client;

import net.fabricmc.api.ModInitializer;

/**
 * Initialize the mod, everything else is handled in playerDeathMixin class
 */
public class Main implements ModInitializer {
    public static final String MOD_ID = "deathShutdownMod";

    @Override
    public void onInitialize() {
        System.out.println("deathShutdownMod v1.0 by 3urobeat loaded.");
    }
}