package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.deserializeLocation
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")
        if (args.isEmpty()) return sender.isStupid("Please specify a warp!")

        val warp = SQL.query("SELECT location FROM warps WHERE name = ?", args[0])

        if (warp.next()) return sender.isStupid("That warp doesn't exist!")

        sender.teleport(deserializeLocation(warp.getString("location")))
        return true
    }
}