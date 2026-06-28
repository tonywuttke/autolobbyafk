package com.opsucht.autolobby;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public class MovementTracker {
    
    /**
     * Prüft ob der Spieler diese Frame sich bewegt
     * (W, A, S, D oder Space drückt)
     */
    public static boolean isMovingThisFrame() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options == null) {
            return false;
        }

        // Array mit allen Bewegungs-Tasten
        KeyBinding[] movementKeys = {
            client.options.keyForward,      // W
            client.options.keyBack,         // S
            client.options.keyLeft,         // A
            client.options.keyRight,        // D
            client.options.keyJump          // Space
        };

        // Prüfe ob irgendeine Bewegungs-Taste gedrückt ist
        for (KeyBinding key : movementKeys) {
            if (key.isPressed()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prüft ob der Spieler sprinted
     * (Optional: könnte auch als Bewegung zählen)
     */
    public static boolean isSprintingThisFrame() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return false;
        }
        return client.player.isSprinting();
    }
}
