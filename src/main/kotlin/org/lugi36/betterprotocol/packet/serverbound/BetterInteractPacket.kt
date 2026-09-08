package org.lugi36.betterprotocol.packet.serverbound

import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.world.InteractionHand
import org.bukkit.Location
import org.bukkit.craftbukkit.util.CraftLocation
import org.bukkit.inventory.EquipmentSlot
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.PacketTypes

class BetterInteractPacket(var entityId: Int, var hand: EquipmentSlot, var loc: Location, var useSecAct: Boolean) : BetterPacket<ServerboundInteractPacket, BetterInteractPacket> {
    constructor(vanilla: ServerboundInteractPacket) : this(
        vanilla.entityId(),
        when (vanilla.hand()){
            InteractionHand.MAIN_HAND -> EquipmentSlot.HAND
            InteractionHand.OFF_HAND -> EquipmentSlot.OFF_HAND
        },
        CraftLocation.toBukkit(vanilla.location()),
        vanilla.usingSecondaryAction()
    )

    override val vanilla: ServerboundInteractPacket
        get() =
            ServerboundInteractPacket(
                entityId,
                when (hand) {
                    EquipmentSlot.HAND -> InteractionHand.MAIN_HAND
                    EquipmentSlot.OFF_HAND -> InteractionHand.OFF_HAND
                    else -> throw IllegalArgumentException()
                },
                CraftLocation.toVec3(loc),
                useSecAct
            )

    override fun type() = PacketTypes.Interact
}