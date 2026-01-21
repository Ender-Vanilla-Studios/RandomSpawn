package net.Mirik9724.RandomSpawn

import net.Mirik9724.api.*
import net.Mirik9724.api.bstats.bukkit.Metrics
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.Random


lateinit var data: Map<String, String>
val pth = "plugins/RandomSpawn/"
val cfg = "config.yml"
val plrs = "sessions.yml"
val dataFile = File(pth+plrs)
var spawnRadius: Int = 0

fun teleportPlayerRandomly(player: Player) {
    val random: Random = Random()

    val x: Int = random.nextInt(2001) - spawnRadius
    val z: Int = random.nextInt(2001) - spawnRadius

    val world: World = player.getWorld()
    val y: Int = world.getHighestBlockYAt(x, z)

    val randomLocation: Location = Location(world, x.toDouble() + 0.5, y.toDouble(), z.toDouble() + 0.5)

    player.teleport(randomLocation)
}

class RandomSpawn : JavaPlugin() {
    override fun onEnable() {
        tryCreatePath(File(pth))
        copyFileFromJar(cfg, pth, this.javaClass.classLoader)
        data = loadYmlFile(pth+cfg)
        updateYmlFromJar(cfg, pth+cfg, this.javaClass.classLoader)

        spawnRadius = data["spawnRadius"]!!.toInt()

        server.pluginManager.registerEvents(LoginEvent(), this)

        if(data["useSpawnCommand"] == "true"){
            getCommand("spawn")?.setExecutor(SpawnCommand())
                ?: getLogger().warning("Ups command not found!")

        }

        if(!dataFile.exists()){
            dataFile.createNewFile()
        }

        if(data["metric"] == "true"){
            Metrics(this, 28966)
        }

        if(data["checkUpdates"] == "true"){
            if(isAvailableNewVersion("https://raw.githubusercontent.com/Ender-Vanilla-Studios/RandomSpawn/refs/heads/master/V", this.description.version) == true){
                getLogger().info("New version available")
            }
        }

        getLogger().info("ON")
    }

    override fun onDisable() {

    }
}