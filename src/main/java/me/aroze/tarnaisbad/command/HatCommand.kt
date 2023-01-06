package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.util.isStupid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object HatCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return sender.isStupid("You aren't a player!")

        val oldhelm = sender.inventory.helmet
        val oldtool = sender.inventory.itemInMainHand

        sender.inventory.helmet = oldtool
        sender.inventory.itemInMainHand = oldhelm

        return true

    }

}