package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.lib.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetHomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("&pConsole cannot execute this command.")
        if (args.isEmpty()) return sender.sendWarning("&pPlease specify a home name.")

        val name = args[0].lowercase()
        val uuid = sender.uniqueId.toString()

        val rs = SQL.query("SELECT COUNT(*) FROM homes WHERE owner = ?", uuid)
        val count = rs.getInt(1)
        if (count >= 10) {
            sender.sendColoured("&pYou have hit the limit of 10 homes.")
            return true
        }

        SQL.execute(
            "INSERT OR REPLACE INTO homes(name, owner, location) VALUES(?, ?, ?)",
            name,
            uuid,
            serializeLocation(sender.location)
        )

        return sender.sendFinalColoured("&pSet home &s$name &pto your location. (&s${count + 1}&p/&s10&p)")
    }
}