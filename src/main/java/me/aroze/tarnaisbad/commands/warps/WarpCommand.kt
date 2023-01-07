package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendWarning
import me.aroze.tarnaisbad.lib.deserializeLocation
import me.aroze.tarnaisbad.lib.translate
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning(translate("must-be-player"))
        if (args.isEmpty()) return sender.sendWarning("Please specify a warp name.")

        val warp = SQL.query("SELECT location FROM warps WHERE name = ?", args[0].lowercase())
        if (!warp.next()) return sender.sendWarning("That warp doesn't exist.")

        sender.teleport(deserializeLocation(warp.getString("location")))
        return true
    }
}