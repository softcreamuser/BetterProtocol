package org.lugi36.betterprotocol.virtual

class VirtualServer {
    private val players = mutableSetOf<VirtualPlayer>()
    private val entities = mutableSetOf<VirtualEntity>()

    fun addPlayer(player: VirtualPlayer){
        players += player
    }

    fun removePlayer(player: VirtualPlayer){
        players -= player
    }

    fun addEntity(entity: VirtualEntity){
        entities -= entity
    }

    fun removeEntity(entity: VirtualEntity){
        entities -= entity
    }

    fun players() : Set<VirtualPlayer> = players
    fun entities() : Set<VirtualEntity> = entities
}