package org.lugi36.betterprotocol.packet.events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.PacketType

class PacketSendEvent(
    override val player: Player,
    override val packet: BetterPacket<*, *>,
    override val type: PacketType<*, *>
): PacketEvent(), Cancellable {
    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    private var cancelled = false

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }
}