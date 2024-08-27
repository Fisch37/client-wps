package de.fisch37.clientwps.api;

import de.fisch37.clientwps.data.WaypointKey;

public interface WPSSender {
    void teleport(WaypointKey target);
}
