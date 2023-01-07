package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.UUID

object SetHomeCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return sender.sendWarning(translate("must-be-player"))
        if (args.isEmpty()) return sender.sendWarning("Please specify a home name.")

        Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
            val name = args[0].lowercase()
            val uuid = sender.uniqueId.toString()

            if (getCount(uuid) >= 10) {
                sender.sendColoured("&pYou have hit the limit of 10 homes.")
                return@Runnable
            }

            SQL.execute(
                "INSERT OR REPLACE INTO homes(name, owner, location) VALUES(?, ?, ?)",
                name,
                uuid,
                serializeLocation(sender.location)
            )

            val count = getCount(uuid)

            sender.sendColoured("&pSet home &s$name &pto your location. (&s${count}&p/&s10&p)")
        })

        return true
    }

    private fun getCount(uuid: String): Int {
        val rs = SQL.query("SELECT COUNT(*) FROM homes WHERE owner = ?", uuid)
        return rs.getInt(1)
    }
}