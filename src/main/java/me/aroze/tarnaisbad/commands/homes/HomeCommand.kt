package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendWarning
import me.aroze.tarnaisbad.lib.deserializeLocation
import me.aroze.tarnaisbad.lib.translate
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object HomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning(translate("must-be-player"))

        val name = if (args.isEmpty()) "home" else args.joinToString(" ")
        val home = SQL.query("SELECT location FROM homes WHERE owner = ? AND name = ?", sender.uniqueId.toString(), name.lowercase())

        if (!home.next()) return sender.sendWarning("That home doesn't exist.")

        sender.teleport(deserializeLocation(home.getString("location")))
        return true
    }
}