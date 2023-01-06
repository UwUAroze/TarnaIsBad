package me.aroze.tarnaisbad.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import me.aroze.tarnaisbad.util.isRightless
import ch.njol.skript.Skript
import org.bukkit.Location

object MigrateCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isRightless("migrate", "You cannot do that.")) return true

        val locations = mutableListOf<Location>()

        // TODO: Get indices of "homes" and "warps" list variables for loop
        listOf<String>().forEach { uuid ->

        }

        // check dms!
    }
}