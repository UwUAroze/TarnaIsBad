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

		val completions = mutableListOf<String>()

		when (chunks[0].substring(1)) {
			"home" -> {
				val rs = SQL.query("SELECT name FROM homes WHERE owner = ?", sender.uniqueId.toString())

				while (rs.next()) {
					val name = rs.getString("name")

					if (name.contains(last, true))
						completions.add(name)
				}
			}
			"warp" -> {
				val rs = SQL.query("SELECT name FROM warps")

				while (rs.next()) {
					val name = rs.getString("name")

					if (name.contains(last, true))
						completions.add(name)
				}
			}
		}

		e.completions = completions
	}
}