package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendColoured
import me.aroze.tarnaisbad.util.serializeLocation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetWarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")
        if (args.isEmpty()) return sender.isStupid("Specify a warp!")

        val name = args[0].lowercase()

        SQL.execute(
            "INSERT OR REPLACE INTO warps(name, location) VALUES(?, ?)",
            name,
            serializeLocation(sender.location)
        )

        sender.sendColoured("&#ffd4e3Set warp &#ffb5c$name &#ffd4e3to your location :)")

        return true
    }
}