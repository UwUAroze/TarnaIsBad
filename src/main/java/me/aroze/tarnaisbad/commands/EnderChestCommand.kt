package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.lib.sendWarning
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EnderChestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("&pConsole cannot execute this command.")

        sender.openInventory(sender.enderChest)
        return true
    }

}