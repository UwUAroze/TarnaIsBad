package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendWarning
import me.aroze.tarnaisbad.lib.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DeleteHomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")

        val name = if (args.isEmpty()) "home" else args[0]

        SQL.execute("DELETE FROM homes WHERE owner = ? AND name = ?", sender.uniqueId.toString(), name)
        sender.sendColoured("&pDeleted home &s$name&p.")

        return true
    }
}