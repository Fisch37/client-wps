package de.fisch37.clientwps.data;

import de.fisch37.clientwps.NullableCodec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Uuids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public record WaypointKey(@Nullable UUID ownerUuid, @NotNull String name, @Nullable String ownerName) {
    public static final PacketCodec<RegistryByteBuf, WaypointKey> PACKET_CODEC = PacketCodec.tuple(
            new NullableCodec<>(Uuids.PACKET_CODEC), WaypointKey::ownerUuid,
            PacketCodecs.STRING, WaypointKey::name,
            new NullableCodec<>(PacketCodecs.STRING), WaypointKey::ownerName,
            WaypointKey::new
    );
}
