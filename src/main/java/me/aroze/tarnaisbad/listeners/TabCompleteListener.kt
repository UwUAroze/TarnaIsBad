package me.aroze.tarnaisbad.listeners

import me.aroze.tarnaisbad.lib.SQL
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.TabCompleteEvent

object TabCompleteListener : Listener {
	@EventHandler
	fun onTabComplete(e: TabCompleteEvent) {
		val sender = e.sender

		if (sender !is Player) return

		val chunks = e.buffer.split(Regex("\\s+"))
		val args = chunks.subList(1, chunks.size)
		val last = args.last()
		val name = chunks[0].substring(1).replace("^tarnaisbad:", "")

		e.completions = when (name) {
			"delhome" -> getHomeCompletions(sender, last)
			"home" -> getHomeCompletions(sender, last)
			"delwarp" -> getWarpCompletions(last)
			"warp" -> getWarpCompletions(last)
			else -> return
		}
	}

	fun getHomeCompletions(owner: Player, last: String): List<String> {
		val completions = mutableListOf<String>()
		val rs = SQL.query("SELECT name FROM homes WHERE owner = ?", owner.uniqueId.toString())

		while (rs.next()) {
			val name = rs.getString("name")

			if (name.contains(last, true))
				completions.add(name)
		}

		return completions
	}

	fun getWarpCompletions(last: String): List<String> {
		val completions = mutableListOf<String>()
		val rs = SQL.query("SELECT name FROM warps")

		while (rs.next()) {
			val name = rs.getString("name")

			if (name.contains(last, true))
				completions.add(name)
		}

		return completions
	}
}