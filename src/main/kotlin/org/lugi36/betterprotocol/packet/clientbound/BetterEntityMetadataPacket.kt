package org.lugi36.betterprotocol.packet.clientbound

import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.SynchedEntityData
import org.lugi36.betterprotocol.packet.BetterPacket
import org.lugi36.betterprotocol.packet.PacketTypes

class BetterEntityMetadataPacket(var entityId: Int, var packedItems: List<SynchedEntityData.DataValue<*>>) : BetterPacket<ClientboundSetEntityDataPacket, BetterEntityMetadataPacket> {
    constructor(vanilla: ClientboundSetEntityDataPacket) : this(
        vanilla.id,
        vanilla.packedItems
    )

    override val vanilla: ClientboundSetEntityDataPacket
        get() =
            ClientboundSetEntityDataPacket(
                entityId,
                packedItems
            )
    override fun type() = PacketTypes.EntityMetaData
}