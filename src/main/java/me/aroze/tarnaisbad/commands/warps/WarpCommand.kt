package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendWarning
import me.aroze.tarnaisbad.lib.deserializeLocation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning("Console cannot execute this command.")
        if (args.isEmpty()) return sender.sendWarning("Please specify a warp name.")

        val warp = SQL.query("SELECT location FROM warps WHERE name = ?", args[0])

        if (warp.next()) return sender.sendWarning("&pThat warp doesn't exist.")

        sender.teleport(deserializeLocation(warp.getString("location")))
        return true
    }
}