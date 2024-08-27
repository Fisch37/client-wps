package de.fisch37.clientwps.packet;

import de.fisch37.clientwps.packet.waypoints.WaypointTeleport;
import de.fisch37.clientwps.packet.waypoints.WaypointUpdate;
import de.fisch37.clientwps.packet.waypoints.WaypointsPayload;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.minecraft.network.packet.CustomPayload.Id;

public abstract class PacketTypes {
    private static final String MOD_ID = "cimple-waypoint-system";

    public static final Id<ClientFeaturesPayload> CLIENT_FEATURES = id("client_features");

    public static final Id<WaypointsPayload> WAYPOINTS = id("waypoints");
    public static final Id<WaypointTeleport> TELEPORT = id("teleport");
    public static final Id<WaypointUpdate> WAYPOINT_UPDATE = id("waypoint_update");

    private static <T extends CustomPayload> Id<T> id(String name) {
        return new Id<>(Identifier.of(MOD_ID, name));
    }

    public static void register() {
        ClientFeaturesPayload.register();

        WaypointsPayload.register();
        WaypointTeleport.register();
        WaypointUpdate.register();
    }
}