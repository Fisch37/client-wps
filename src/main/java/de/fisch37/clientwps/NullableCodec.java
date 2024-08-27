package de.fisch37.clientwps;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record NullableCodec<B extends ByteBuf, V>(PacketCodec<B, V> parent) implements PacketCodec<B, V> {

    @Override
    public V decode(B buf) {
        return PacketByteBuf.readNullable(buf, parent);
    }

    @Override
    public void encode(B buf, V value) {
        PacketByteBuf.writeNullable(buf, value, parent);
    }
}
