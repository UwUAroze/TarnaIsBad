package me.aroze.tarnaisbad.commands.admin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.Location

object MigrateCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val locations = mutableListOf<Location>()

        // TODO: Get indices of "homes" and "warps" list variables for loop
        listOf<String>().forEach { uuid ->

        }

        return true
    }
}