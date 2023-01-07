package me.aroze.tarnaisbad.commands.admin

import me.aroze.tarnaisbad.lib.handleTarget
import me.aroze.tarnaisbad.lib.hasPermission
import me.aroze.tarnaisbad.lib.sendWarning
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenEnderchestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")
        if (sender.hasPermission("enderchest.others", "&pYou can only open your own ender chest.")) return true
        val target = handleTarget(sender, args) ?: return true

        sender.openInventory(target.enderChest)
        return true
    }

}