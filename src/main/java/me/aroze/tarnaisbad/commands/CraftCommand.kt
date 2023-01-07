package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.lib.sendWarning
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType

object CraftCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")
        if (!sender.inventory.contains(Material.CRAFTING_TABLE)) return sender.sendWarning("You need a crafting table in your inventory.")

        sender.openInventory(sender.server.createInventory(sender, InventoryType.CRAFTING))

        return true
    }

}