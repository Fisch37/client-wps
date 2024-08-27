package de.fisch37.clientwps;

import de.fisch37.clientwps.api.WPSSender;
import de.fisch37.clientwps.data.WaypointKey;
import de.fisch37.clientwps.packet.waypoints.WaypointTeleport;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

class WPSSenderImpl implements WPSSender {

    @Override
    public void teleport(WaypointKey target) {
        ClientPlayNetworking.send(new WaypointTeleport(target));
    }
}
