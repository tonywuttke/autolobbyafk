package com.opsucht.autolobby;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AFKTimer {
    private static final Logger LOGGER = LoggerFactory.getLogger("autolobby");
    
    public static long lastMovementTime = System.currentTimeMillis();
    private static boolean warningShown = false;
    
    // Zeiten in Millisekunden
    private static final long WARNING_TIME = 3 * 60 * 1000;  // 3 Minuten
    private static final long LOGOUT_TIME = 5 * 60 * 1000;   // 5 Minuten

    /**
     * Wird jeden Client Tick aufgerufen (60 Hz)
     */
    public static void onTick() {
        long now = System.currentTimeMillis();
        long timeSinceLastMovement = now - lastMovementTime;

        // Wenn Spieler sich JETZT bewegt → Timer reset
        if (MovementTracker.isMovingThisFrame()) {
            if (timeSinceLastMovement > 100) {  // Nur reset wenn wirklich inaktiv war
                LOGGER.debug("Movement detected - resetting AFK timer");
                lastMovementTime = now;
                warningShown = false;
                WarningHUD.hide();
            }
            return;
        }

        // Nach 3 Minuten → Warning anzeigen
        if (timeSinceLastMovement >= WARNING_TIME && !warningShown) {
            LOGGER.info("AFK Warning triggered - player has been inactive for 3 minutes");
            WarningHUD.show();
            warningShown = true;
        }

        // Nach 5 Minuten → Automatisch zur Lobby
        if (timeSinceLastMovement >= LOGOUT_TIME) {
            LOGGER.info("AFK Logout triggered - executing /lobby command");
            executeLobbyCcommand();
            resetTimer();
        }
    }

    /**
     * Setzt den Timer zurück
     */
    public static void resetTimer() {
        lastMovementTime = System.currentTimeMillis();
        warningShown = false;
        WarningHUD.hide();
        LOGGER.info("AFK Timer reset");
    }

    /**
     * Führt den /lobby Befehl aus
     */
    private static void executeLobbyCcommand() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getNetworkHandler() != null) {
            client.getNetworkHandler().sendChatCommand("lobby");
        }
    }

    /**
     * Gibt die Zeit seit der letzten Bewegung in Sekunden zurück
     */
    public static long getTimeSinceLastMovementSeconds() {
        return (System.currentTimeMillis() - lastMovementTime) / 1000;
    }

    /**
     * Gibt die Zeit bis zum Logout in Sekunden zurück
     */
    public static long getTimeUntilLogoutSeconds() {
        long timeLeft = LOGOUT_TIME - (System.currentTimeMillis() - lastMovementTime);
        return Math.max(0, timeLeft / 1000);
    }

    /**
     * Gibt die Zeit bis zur Warning in Sekunden zurück
     */
    public static long getTimeUntilWarningSeconds() {
        long timeLeft = WARNING_TIME - (System.currentTimeMillis() - lastMovementTime);
        return Math.max(0, timeLeft / 1000);
    }
}
