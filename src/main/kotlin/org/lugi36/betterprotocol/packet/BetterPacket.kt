package org.lugi36.betterprotocol.packet

import net.minecraft.network.protocol.Packet

interface BetterPacket<T: Packet<*>, S: BetterPacket<T, S>> {
    val vanilla: T
    fun type() : PacketType<T, S>
}