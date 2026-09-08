package org.lugi36.betterprotocol.packet

import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import org.lugi36.betterprotocol.packet.clientbound.BetterAnimatePacket
import org.lugi36.betterprotocol.packet.clientbound.BetterEntityMetadataPacket
import org.lugi36.betterprotocol.packet.serverbound.BetterInteractPacket

object PacketTypes {

    val Interact = PacketType(
        ServerboundInteractPacket::class.java,
        BetterInteractPacket::class.java,
        "serverbound_interact_packet"
    ){ packet ->
        BetterInteractPacket(packet)
    }

    val Animate = PacketType(
        ClientboundAnimatePacket::class.java,
        BetterAnimatePacket::class.java,
        "clientbound_animate_packet"
    ){ packet ->
        BetterAnimatePacket(packet)
    }

    val EntityMetaData = PacketType(
        ClientboundSetEntityDataPacket::class.java,
        BetterEntityMetadataPacket::class.java,
        "clientbound_entityMetadata_packet"
    ){ packet ->
        BetterEntityMetadataPacket(packet)
    }

    val types
        get() = listOf(
            Interact, Animate, EntityMetaData
        )
}