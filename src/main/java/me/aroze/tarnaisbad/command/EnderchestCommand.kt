package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.util.isStupid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EnderchestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")

        sender.openInventory(sender.enderChest)
        return true
    }

}