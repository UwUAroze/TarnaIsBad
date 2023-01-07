package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.lib.coloured
import me.aroze.tarnaisbad.lib.sendWarning
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object RenameCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")
        if (args.isEmpty()) return sender.sendWarning("Please specify a name.")
        if (sender.level < 1) return sender.sendWarning("You don't have enough experience.")
        if (!(sender.inventory.contains(Material.ANVIL) ||
              sender.inventory.contains(Material.CHIPPED_ANVIL) ||
              sender.inventory.contains(Material.DAMAGED_ANVIL)))
                return sender.sendWarning("You need an anvil in your inventory.")

        val rename = args.joinToString(" ")
        val item = sender.inventory.itemInMainHand

        if (rename.length > 100) return sender.sendWarning("&pThere is a character limit of 100.")
        if (item.type.isAir) return sender.sendWarning("&pYou need to hold an item.")

        val meta = item.itemMeta!!
        meta.setDisplayName(rename.coloured())
        item.itemMeta = meta
        sender.level -= 1

        return true
    }

}