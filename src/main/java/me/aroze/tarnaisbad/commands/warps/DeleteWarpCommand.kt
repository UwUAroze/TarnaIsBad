package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.sendColoured
import me.aroze.tarnaisbad.lib.sendFinalColoured
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object DeleteWarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
            SQL.execute("DELETE FROM warps WHERE name = ?", args[0])
            sender.sendColoured("&pDeleted warp &s${args[0]}&p.")
        })

        return true
    }
}