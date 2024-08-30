package de.fisch37.clientwps;

import de.fisch37.clientwps.api.WPSClientInitializer;
import de.fisch37.clientwps.packet.PacketTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientWps implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("cWPS Client");
    private WPSClientInitializer initializer;

    @Override
    public void onInitializeClient() {
        PacketTypes.register();
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            FabricLoader.getInstance().getEntrypoints("cwps", WPSClientInitializer.class)
                    .forEach(this::clientInit);
        });
        registerReceivers();
        LOGGER.info("ClientWps initialized");
    }

    private void clientInit(WPSClientInitializer initializer) {
        this.initializer = initializer;
        initializer.onCWPSInitialize(new WPSSenderImpl());
        ClientPlayNetworking.send(initializer.getFeatures());
    }

    private void registerReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(PacketTypes.WAYPOINTS, ((payload, context) -> {
            initializer.onWaypoints(payload.waypoints());
        }));
        ClientPlayNetworking.registerGlobalReceiver(PacketTypes.WAYPOINT_UPDATE, ((payload, context) -> {
            initializer.onWaypointUpdate(payload.waypointInfo());
        }));
    }
}
