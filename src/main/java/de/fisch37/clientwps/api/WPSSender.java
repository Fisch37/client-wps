package de.fisch37.clientwps.api;

import de.fisch37.clientwps.data.WaypointKey;

public interface WPSSender {
    /**
     * Teleport the player to the given waypoint
     * @implNote Note that this method does nothing if the player can't access the waypoint.
     *  Admins should be teleported using a /tp instead.
     * @param target The Waypoint to teleport to
     */
    void teleport(WaypointKey target);
}
