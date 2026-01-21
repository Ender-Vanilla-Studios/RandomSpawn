package net.Mirik9724.RandomSpawn

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class LoginEvent : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (!event.player.hasPlayedBefore()) {
            teleportPlayerRandomly(event.player)
        }
    }

    @EventHandler
    fun onPlayerRespawn(event: org.bukkit.event.player.PlayerRespawnEvent) {
        val player = event.player

        if (player.getBedSpawnLocation() == null) {
            val random = java.util.Random()

            val range = spawnRadius * 2 + 1
            val x = random.nextInt(range) - spawnRadius
            val z = random.nextInt(range) - spawnRadius

            val world = player.world
            val y = world.getHighestBlockYAt(x, z)

            val respawnLocation = org.bukkit.Location(world, x.toDouble() + 0.5, y.toDouble(), z.toDouble() + 0.5)

            event.respawnLocation = respawnLocation
        }
    }
}
