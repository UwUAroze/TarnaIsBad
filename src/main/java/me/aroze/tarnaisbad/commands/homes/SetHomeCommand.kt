package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendColoured
import me.aroze.tarnaisbad.util.serializeLocation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetHomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")
        if (args.isEmpty()) return sender.isStupid("Please specify a home!") // you're learning!

        val name = args[0].lowercase()

        SQL.execute(
            "INSERT OR REPLACE INTO homes(name, owner, location) VALUES(?, ?, ?)",
            name,
            sender.uniqueId.toString(),
            serializeLocation(sender.location)
        )

        sender.sendColoured("&#ffd4e3Set home &#ffb5c$name &#ffd4e3to your location :)")

        return true
    }
}