package me.aroze.tarnaisbad.commands.admin

import me.aroze.tarnaisbad.util.handleTarget
import me.aroze.tarnaisbad.util.isRightless
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenEnderchestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.isRightless("enderchest.others", "You can only open your own enderchest (/ec)")) return true
        val target = handleTarget(sender, args) ?: return true

        (sender as Player).openInventory(target.enderChest)
        return true
    }

}