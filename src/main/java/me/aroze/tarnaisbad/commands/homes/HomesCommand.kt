package me.aroze.tarnaisbad.commands.homes

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object HomesCommand : CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender !is Player) return sender.sendWarning(translate("must-be-player"))

		Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
			val rs = SQL.query("SELECT name FROM homes WHERE owner = ?", sender.uniqueId.toString())
			val homes = mutableListOf<String>()

			while (rs.next())
				homes.add(rs.getString("name"))

			sender.sendColoured("&pHomes (&s${homes.size}&p): &s${homes.joinToString("&p, &s")}")
		})

		return true
	}
}