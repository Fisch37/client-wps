package de.fisch37.clientwps.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public enum AccessLevel {
    SECRET("secret"),
    PRIVATE("private"),
    PUBLIC("public"),
    OPEN("open");

    public static final PacketCodec<RegistryByteBuf, AccessLevel> PACKET_CODEC = PacketCodec.of(
            (val, buf) -> buf.writeByte(val.ordinal()),
            buf -> AccessLevel.values()[buf.readByte()]
    );

    public final String name;

    AccessLevel(String name) {
        this.name = name;
    }

    public static AccessLevel fromString(String name) throws IllegalArgumentException {
        return switch (name) {
            case "secret" -> AccessLevel.SECRET;
            case "private" -> AccessLevel.PRIVATE;
            case "public" -> AccessLevel.PUBLIC;
            case "open" ->  AccessLevel.OPEN;
            default -> throw new IllegalArgumentException(name + " is not an acceptable access name.");
        };
    }
}
