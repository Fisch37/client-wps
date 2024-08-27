package de.fisch37.clientwps.api;

import de.fisch37.clientwps.packet.ClientFeaturesPayload;
import de.fisch37.clientwps.packet.waypoints.WaypointInfo;

import java.util.List;

public interface WPSClientInitializer {
    void onCWPSInitialize(WPSSender sender);

    ClientFeaturesPayload getFeatures();

    default void onWaypoints(List<WaypointInfo> waypoints) { }

    default void onWaypointUpdate(WaypointInfo waypoint) { }
}
