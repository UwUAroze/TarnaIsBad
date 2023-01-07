package me.aroze.tarnaisbad.listeners

import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinListener : Listener {
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        if (!player.hasPlayedBefore())
            player.teleport(player.world.spawnLocation)
    }
}