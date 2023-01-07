package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendWarning
import me.aroze.tarnaisbad.lib.sendColoured
import me.aroze.tarnaisbad.lib.serializeLocation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetWarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")
        if (args.isEmpty()) return sender.sendWarning("Please specify a warp name.")

        val name = args[0].lowercase()

        SQL.execute(
            "INSERT OR REPLACE INTO warps(name, location) VALUES(?, ?)",
            name,
            serializeLocation(sender.location)
        )

        sender.sendColoured("&pSet warp &s$name &pto your location.")

        return true
    }
}