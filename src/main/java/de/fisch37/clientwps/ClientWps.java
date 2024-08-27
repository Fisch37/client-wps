package de.fisch37.clientwps;

import de.fisch37.clientwps.api.WPSClientInitializer;
import de.fisch37.clientwps.packet.PacketTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;

public class ClientWps implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PacketTypes.register();
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            FabricLoader.getInstance().getEntrypoints("cwps", WPSClientInitializer.class)
                    .forEach(this::clientInit);
        });
    }

    private void clientInit(WPSClientInitializer initializer) {
        initializer.onCWPSInitialize(new WPSSenderImpl());
        ClientPlayNetworking.send(initializer.getFeatures());

    }
}
