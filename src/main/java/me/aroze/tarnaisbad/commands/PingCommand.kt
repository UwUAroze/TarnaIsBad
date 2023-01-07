package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.lib.handleTarget
import me.aroze.tarnaisbad.lib.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object PingCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val target = handleTarget(sender, args, "&pThat player isn't online.") ?: return true
        if (target == sender) sender.sendColoured("&pYour ping is &s${target.ping}ms&p.")
        else sender.sendColoured("&s${target.name}&p's ping is &s${target.ping}ms&p.")

        return true;
    }

}