package de.fisch37.clientwps;

import de.fisch37.clientwps.packet.PacketTypes;
import net.fabricmc.api.ClientModInitializer;

public class ClientWps implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PacketTypes.register();
    }
}
