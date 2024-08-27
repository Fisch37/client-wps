package de.fisch37.clientwps.packet.waypoints;

import de.fisch37.clientwps.data.WaypointKey;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

import static de.fisch37.clientwps.packet.PacketTypes.TELEPORT;

public record WaypointTeleport(WaypointKey target) implements CustomPayload {
    public static final PacketCodec<RegistryByteBuf, WaypointTeleport> PACKET_CODEC = PacketCodec.tuple(
            WaypointKey.PACKET_CODEC, WaypointTeleport::target,
            WaypointTeleport::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return TELEPORT;
    }

    public static void register() {
        PayloadTypeRegistry.playC2S().register(TELEPORT, PACKET_CODEC);
    }
}
