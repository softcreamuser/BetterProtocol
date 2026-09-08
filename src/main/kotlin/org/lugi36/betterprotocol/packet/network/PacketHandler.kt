package org.lugi36.betterprotocol.packet.network

import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Pose
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.lugi36.betterprotocol.BetterProtocolPlugin
import org.lugi36.betterprotocol.packet.PacketRegistry
import org.lugi36.betterprotocol.packet.events.PacketReceiveEvent
import org.lugi36.betterprotocol.packet.events.PacketSendEvent

class PacketHandler(val player: Player) : ChannelDuplexHandler() {

    override fun channelRead(
        ctx: ChannelHandlerContext,
        msg: Any
    ) {
        val msg = msg

        if (msg is Packet<*>){
            val type = PacketRegistry.find(msg)

            if (type != null) {
                val packet = type.wrapAny(msg)
                val event = PacketReceiveEvent(
                    player,
                    packet,
                    type
                )

                if (packet != null){
                    BetterProtocolPlugin.plugin.server.pluginManager.callEvent(
                        event
                    )

                    if (event.isCancelled) return
                }
            }
        }

        super.channelRead(ctx, msg)
    }

    override fun write(
        ctx: ChannelHandlerContext,
        msg: Any,
        promise: ChannelPromise
    ) {
        if (msg is Packet<*>) {
            val type = PacketRegistry.find(msg)

            if (type != null) {
                val packet = PacketRegistry.wrap(msg)

                if (packet != null) {
                    val event = PacketSendEvent(
                        player,
                        packet,
                        type
                    )



                    Bukkit.getPluginManager().callEvent(event)

                    if (event.isCancelled) {
                        return
                    }
                }
            }
        }

        super.write(ctx, msg, promise)
    }
}