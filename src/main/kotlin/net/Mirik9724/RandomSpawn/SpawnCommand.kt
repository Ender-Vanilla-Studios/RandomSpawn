package net.Mirik9724.RandomSpawn

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class SpawnCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command?, label: String?, args: Array<String?>?): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command can only be executed by players")
            return true
        }

        teleportPlayerRandomly(sender)
        return true
    }
}