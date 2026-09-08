package org.lugi36.betterprotocol.packet.clientbound

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityTypes
import net.minecraft.world.entity.monster.zombie.Zombie
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.craftbukkit.util.CraftLocation
import org.bukkit.entity.Entity
import org.bukkit.inventory.EquipmentSlot
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.PacketType
import org.lugi36.betterprotocol.packet.PacketTypes
import org.lugi36.betterprotocol.packet.serverbound.BetterInteractPacket

class BetterAnimatePacket(var id: Int, var action: Int) : BetterPacket<ClientboundAnimatePacket, BetterAnimatePacket> {
    constructor(vanilla: ClientboundAnimatePacket) : this(
        vanilla.id,
        vanilla.action
    )

    override val vanilla: ClientboundAnimatePacket
        get() =
            ClientboundAnimatePacket(Zombie(
                EntityTypes.ZOMBIE, (Bukkit.getWorlds()[0] as CraftWorld).handle).apply {
                    id = this@BetterAnimatePacket.id
            }, action )
    override fun type() = PacketTypes.Animate
}