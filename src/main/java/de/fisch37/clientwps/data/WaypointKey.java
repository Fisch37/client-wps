package de.fisch37.clientwps.data;

import de.fisch37.clientwps.NullableCodec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Uuids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public record WaypointKey(@Nullable UUID ownerUuid, @NotNull String name, @Nullable String ownerName) implements Comparable<WaypointKey> {
    public static final PacketCodec<RegistryByteBuf, WaypointKey> PACKET_CODEC = PacketCodec.tuple(
            new NullableCodec<>(Uuids.PACKET_CODEC), WaypointKey::ownerUuid,
            PacketCodecs.STRING, WaypointKey::name,
            new NullableCodec<>(PacketCodecs.STRING), WaypointKey::ownerName,
            WaypointKey::new
    );

    @Override
    public int hashCode() {
        if ( this.ownerName == null ) return name.toLowerCase().hashCode();
        return ownerName.hashCode() * name.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof WaypointKey that) ) return false;

        final boolean sameName = this.name.equalsIgnoreCase(that.name);
        return sameName && Objects.equals(ownerUuid, that.ownerUuid);
    }

    /**
     * Compares two {@link WaypointKey} objects according to their lexicographical ordering.
     * <p>
     * There are two steps to the comparison:
     * 1. Comparison of ownership. If two {@link WaypointKey} objects have different owners,
     *      the ordering of the keys matches that of their owners (by name).
     *      Unowned waypoints are always considered less than owned waypoints.
     * 2. Comparison of name. If two {@link WaypointKey} objects have the same owners (or both have no owner),
     *      their ordering is determined by the lexicographical ordering of their names.
     * <p>
     * <em>API Note:</em> For consistency, the only possible values returned by this comparator are -1, 0, 1.
     */
    @Override
    public int compareTo(@NotNull WaypointKey that) {
        if (Objects.equals(this.ownerUuid, that.ownerUuid)) {
            return Math.clamp(this.name.compareToIgnoreCase(that.name), -1, 1);
        } else {
            if (this.ownerName == null)
                return -1;
            else if (that.ownerName == null)
                return 1;
            return Math.clamp(this.ownerName.compareTo(that.ownerName), -1, 1);
        }
    }
}
