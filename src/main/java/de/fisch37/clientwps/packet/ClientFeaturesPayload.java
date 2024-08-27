package de.fisch37.clientwps.packet;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

import static de.fisch37.clientwps.packet.PacketTypes.CLIENT_FEATURES;


public record ClientFeaturesPayload(int features) implements CustomPayload {
    public static PacketCodec<RegistryByteBuf, ClientFeaturesPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, ClientFeaturesPayload::features,
            ClientFeaturesPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return CLIENT_FEATURES;
    }

    private static int getFeatureFlag(int featureNum) {
        return 1<<featureNum;
    }

    private boolean hasFeature(int featureNum) {
        return (this.features & (getFeatureFlag(featureNum))) != 0;
    }

    public boolean waypoints() {
        return this.hasFeature(0);
    }

    public boolean waypointsManagement() {
        return this.hasFeature(1);
    }


    public static void register() {
        PayloadTypeRegistry.playC2S().register(CLIENT_FEATURES, PACKET_CODEC);
    }

    public static class Builder {
        private int features = 0;

        public Builder() { }

        public Builder withWaypoints() {
            this.features |= getFeatureFlag(0);
            return this;
        }

        public Builder withWaypointsManagement() {
            this.features |= getFeatureFlag(1);
            return this;
        }

        public ClientFeaturesPayload build() {
            return new ClientFeaturesPayload(this.features);
        }
    }
}
