package de.fisch37.clientwps.api;

import de.fisch37.clientwps.packet.ClientFeaturesPayload;
import de.fisch37.clientwps.packet.waypoints.WaypointInfo;

import java.util.List;

public interface WPSClientInitializer {
    /**
     * Called when the player has joined a server (regardless whether the server has cWPS installed)
     * @param sender Object used to send commands to the server
     */
    void onCWPSInitialize(WPSSender sender);

    /**
     * The features this entrypoint requires.
     * Note that not enabling a feature does not guarantee its respective methods won't be called.
     * @return The features supported by this entrypoint
     */
    ClientFeaturesPayload getFeatures();

    /**
     * Called when the server sends waypoints.
     * @param waypoints The received {@link WaypointInfo} objects
     */
    default void onWaypoints(List<WaypointInfo> waypoints) { }

    /**
     * Called when a waypoint has changed
     * @param waypoint The new {@link WaypointInfo} object
     */
    default void onWaypointUpdate(WaypointInfo waypoint) { }
}
