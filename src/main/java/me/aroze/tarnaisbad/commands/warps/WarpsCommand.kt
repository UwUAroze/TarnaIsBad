package me.aroze.tarnaisbad.commands.warps

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WarpsCommand : CommandExecutor {
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender !is Player) return sender.sendWarning(translate("must-be-player"))

		Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
			val rs = SQL.query("SELECT name FROM warps")
			val warps = mutableListOf<String>()

			while (rs.next())
				warps.add(rs.getString("name"))

			sender.sendColoured("&pWarps (&s${warps.size}&p): &s${warps.joinToString("&p, &s")}")
		})

		return true
	}
}