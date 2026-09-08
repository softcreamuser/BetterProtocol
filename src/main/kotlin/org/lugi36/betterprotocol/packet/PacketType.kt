package org.lugi36.betterprotocol.packet

import net.minecraft.network.protocol.Packet

class PacketType<T: Packet<*>, S: BetterPacket<T, S>>(
    val vanillaClass: Class<T>,
    val clazz: Class<S>,
    val name: String,
    val factory: (T) -> S
){
    fun wrap(packet: T): S {
        return factory(packet)
    }

    @Suppress("UNCHECKED_CAST")
    fun wrapAny(packet: Packet<*>): S {
        return factory(packet as T)
    }
}