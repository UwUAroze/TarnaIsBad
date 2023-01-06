package me.aroze.tarnaisbad.command

import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DeleteWarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.isStupid("You aren't a player!")

        SQL.execute("DELETE FROM warps WHERE name = ?", args[0])
        sender.sendColoured("&#ffd4e3Deleted warp &#ffb5c${args[0]}&#ffd4e3.")

        return true
    }
}