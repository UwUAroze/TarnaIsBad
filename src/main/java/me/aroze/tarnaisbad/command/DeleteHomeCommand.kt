package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DeleteHomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")

        val name = if (args.isEmpty()) "home" else args[0]

        SQL.execute("DELETE FROM homes WHERE owner = ? AND name = ?", sender.uniqueId.toString(), name)
        sender.sendColoured("&#ffd4e3Deleted home &#ffb5c$name&#ffd4e3.")

        return true
    }
}