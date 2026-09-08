package org.lugi36.betterprotocol.virtual

import io.netty.channel.Channel
import net.minecraft.network.protocol.Packet
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.events.PacketSendEvent
import org.lugi36.betterprotocol.packet.network.PacketHandler
import org.lugi36.betterprotocol.registry

class PlayerConnection(
    val player: Player,
    val channel: Channel
) {
    init {
        channel.pipeline().addBefore(
            "packet_handler",
            "better_protocol",
            PacketHandler(player)
        )
    }

    fun send(packet: Packet<*>){
        channel.writeAndFlush(packet)
    }

    fun send(packet: BetterPacket<*, *>){
        send(packet.vanilla)
    }

    fun isOpen(): Boolean{
        return channel.isOpen
    }

    fun isActive(): Boolean{
        return channel.isActive
    }

    companion object{
        fun from(player: Player): PlayerConnection{
            val handle = (player as CraftPlayer).handle
            return PlayerConnection(player, handle.connection.connection.channel)
        }
    }
}