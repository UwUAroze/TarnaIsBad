package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SpawnCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return sender.isStupid("You aren't a player!")

        // TODO: Replace this with actual spawn variable
        sender.teleport(sender.world.spawnLocation)
        return sender.sendFinalColoured("&#ffd4e3look, its spawn!")

    }

}