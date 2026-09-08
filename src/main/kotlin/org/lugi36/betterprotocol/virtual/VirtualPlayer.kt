package org.lugi36.betterprotocol.virtual

import org.bukkit.entity.Player
import org.lugi36.betterprotocol.packet.BetterPacket
import java.util.UUID

class VirtualPlayer(val player: Player) {
    val uuid: UUID
        get() = player.uniqueId

    val connection = PlayerConnection.from(player)

    val name: String
        get() = player.name

    fun send(packet: BetterPacket<*, *>){
        connection.send(packet)
    }
}