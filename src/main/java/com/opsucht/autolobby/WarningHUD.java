package com.opsucht.autolobby;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class WarningHUD {
    private static final Logger LOGGER = LoggerFactory.getLogger("autolobby");
    
    private static boolean isVisible = false;
    private static boolean soundPlayed = false;

    /**
     * Zeigt die Warning an
     */
    public static void show() {
        isVisible = true;
        soundPlayed = false;  // Sound muss neu gespielt werden
    }

    /**
     * Versteckt die Warning
     */
    public static void hide() {
        isVisible = false;
        soundPlayed = false;
    }

    /**
     * Gibt zurück ob Warning sichtbar ist
     */
    public static boolean isVisible() {
        return isVisible;
    }

    /**
     * Rendert die Warning auf dem HUD
     */
    public static void render(DrawContext context, int screenWidth, int screenHeight) {
        if (!isVisible) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();

        // Sound nur 1x abspielen
        if (!soundPlayed) {
            playWarningSound();
            soundPlayed = true;
        }

        // Berechne verbleibende Zeit
        long timeUntilLogout = AFKTimer.getTimeUntilLogoutSeconds();
        long minutesLeft = timeUntilLogout / 60;
        long secondsLeft = timeUntilLogout % 60;

        // Erstelle Warning-Text
        String warningText = "Du wirst in " + minutesLeft + " Minute" + 
                           (minutesLeft == 1 ? "" : "n") + 
                           " in die Lobby gesendet!";

        // Text-Width berechnen
        int textWidth = client.textRenderer.getWidth(warningText);
        
        // Position: Mittig im Bildschirm
        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight / 2 - 30;

        // Zeichne roten Hintergrund (semi-transparent)
        // Rot: 0xFF3333 mit 0.8 Transparenz = 0xCC3333FF
        fill(context, x - 20, y - 10, x + textWidth + 20, y + 25, 0xDD3333FF);

        // Zeichne weißen Text
        context.drawText(
            client.textRenderer,
            warningText,
            x, y,
            0xFFFFFF,  // Weiß
            false
        );
    }

    /**
     * Füllt ein Rechteck mit einer Farbe
     */
    private static void fill(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        context.fill(x1, y1, x2, y2, color);
    }

    /**
     * Spielt den Warning-Sound ab
     */
    private static void playWarningSound() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null) {
            try {
                // Note Block Pling Sound - hohes Pitch für Warning-Effekt
                PositionedSoundInstance sound = PositionedSoundInstance.ui(
                    SoundEvents.BLOCK_NOTE_BLOCK_PLING,
                    1.5f  // Pitch (höher = schriller)
                );
                client.getSoundManager().play(sound);
                LOGGER.debug("Warning sound played");
            } catch (Exception e) {
                LOGGER.warn("Could not play warning sound: " + e.getMessage());
            }
        }
    }
}
