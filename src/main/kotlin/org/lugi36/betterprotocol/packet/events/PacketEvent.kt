package org.lugi36.betterprotocol.packet.events

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.lugi36.betterprotocol.BetterProtocolPlugin
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.PacketType

abstract class PacketEvent : Event(true) {
    abstract val player: Player
    abstract val packet: BetterPacket<*, *>
    abstract val type: PacketType<*, *>

    inline fun <reified T: BetterPacket<*, *>> packetAs() : T?{
        return packet as? T
    }

    fun executeSync(action: () -> Unit){
        if (BetterProtocolPlugin.plugin.server.isPrimaryThread){
            action()
        } else {
            Bukkit.getScheduler().runTask(BetterProtocolPlugin.plugin, Runnable { action() })
        }
    }
}