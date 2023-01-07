package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.util.isStupid
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType

object CraftCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return sender.isStupid("Only players can craft!")
        if (!sender.inventory.contains(Material.CRAFTING_TABLE)) return sender.isStupid("You need a crafting table to use /craft!")

        sender.openInventory(sender.server.createInventory(sender, InventoryType.CRAFTING))

        return true

    }

}