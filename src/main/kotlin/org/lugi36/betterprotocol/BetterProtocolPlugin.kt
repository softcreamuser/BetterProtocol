package org.lugi36.betterprotocol

import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.lugi36.betterprotocol.packet.PacketTypes
import org.lugi36.betterprotocol.packet.clientbound.BetterAnimatePacket
import org.lugi36.betterprotocol.packet.clientbound.BetterEntityMetadataPacket
import org.lugi36.betterprotocol.packet.events.PacketSendEvent
import org.lugi36.betterprotocol.packet.network.PacketHandler
import org.lugi36.betterprotocol.packet.serverbound.BetterInteractPacket
import org.lugi36.betterprotocol.virtual.PlayerConnection

class BetterProtocolPlugin : JavaPlugin() {
    companion object {
        lateinit var plugin: BetterProtocolPlugin
        private set
    }

    override fun onEnable() {
        // Plugin startup logic
        plugin = this

        PacketTypes.types.forEach { registry.register(it) }

        server.pluginManager.registerEvents(object : Listener {
            @EventHandler
            fun onJoin(event: PlayerJoinEvent){
                val player = event.player

                val serverPlayer = (player as CraftPlayer).handle
                val channel = serverPlayer.connection.connection.channel
                PlayerConnection(player, channel)
            }

            @EventHandler
            fun onReceivePacket(event: PacketSendEvent){
                val packet = event.packetAs<BetterEntityMetadataPacket>()
                    ?: return
                event.isCancelled = true
                println(
                    "[BetterProtocol] " +
                            "${event.player.name} <- ${event.type.name}"
                )
            }
        }, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
