package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetWarpCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning(translate("must-be-player"))
        if (args.isEmpty()) return sender.sendWarning("Please specify a warp name.")

        Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
            val name = args.joinToString(" ")

            SQL.execute(
                "INSERT OR REPLACE INTO warps(name, location) VALUES(?, ?)",
                name,
                serializeLocation(sender.location)
            )

            sender.sendColoured("&pSet warp &s$name &pto your location.")
        })

        return true
    }
}