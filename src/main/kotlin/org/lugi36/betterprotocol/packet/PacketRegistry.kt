package org.lugi36.betterprotocol.packet

import net.minecraft.network.protocol.Packet

object PacketRegistry {

    private val types =
        mutableMapOf<Class<*>, PacketType<*, *>>()

    fun register(type: PacketType<*, *>) {
        types[type.vanillaClass] = type
    }

    fun find(
        packet: Packet<*>
    ): PacketType<*, *>? {
        return types[packet.javaClass]
    }

    @Suppress("UNCHECKED_CAST")
    fun wrap(
        packet: Packet<*>
    ): BetterPacket<*, *>? {

        val type = find(packet) ?: return null

        return (type as PacketType<
                Packet<*>,
                BetterPacket<Packet<*>, *>
                >).factory(packet)
    }
}