package com.opsucht.autolobby;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoLobbyMod implements ClientModInitializer {
	public static final String MOD_ID = "autolobby";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Auto Lobby AFK Mod...");

		// Registriere Client Tick Event
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.world != null && client.player != null) {
				AFKTimer.onTick();
			}
		});

		// Registriere HUD Render Event
		HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.world != null && client.player != null) {
				WarningHUD.render(
					guiGraphics,
					client.getWindow().getScaledWidth(),
					client.getWindow().getScaledHeight()
				);
			}
		});

		LOGGER.info("Auto Lobby AFK Mod initialized successfully!");
	}
}
