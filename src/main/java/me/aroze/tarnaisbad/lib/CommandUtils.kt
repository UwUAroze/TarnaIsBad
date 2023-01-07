package me.aroze.tarnaisbad.lib

import me.aroze.tarnaisbad.TarnaIsBad.Companion.getInstance
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Commonly found target logic for basic commands, heal, fly, feed, etc:
// - If no args are specified, target is sender. Else target is first arg
// - Sender can't be console with no args
// - Target must be online

fun handleTarget(sender: CommandSender, args: Array<out String>, offlinePlayerError: String = "That player doesn't exist, dummy") : Player? {
    if (args.isEmpty()) {
        if (sender is Player) return sender
        sender.sendWarning("&pPlease specify a player."); return null
    }
    if (Bukkit.getPlayer(args[0]) == null) sender.sendWarning(offlinePlayerError)
    return Bukkit.getPlayer(args[0])
}

// Used for incorrect syntaxes/etc; generic error message format.
fun CommandSender.sendWarning(message: String) : Boolean {
    this.sendColoured("&c⚠ $message")
    return true
}

// Permission check
// Should be used like: if (sender.hasPermission("smite")) return true
fun CommandSender.hasPermission(permission: String, message: String = "lol u wish") : Boolean {
    if (this.hasPermission("tarnaisbad.$permission")) return false
    return this.sendWarning(message)
}

// Allows for syntax like 'return sender.sendFinalMessage("done)'
// Rather than needing 2 lines for it, and sometimes curly brackets and indents and ews
fun CommandSender.sendFinalMessage(message: String) : Boolean {
    this.sendMessage(message)
    return true
}

fun CommandSender.sendFinalColoured(message: String) : Boolean {
    this.sendColoured(message)
    return true
}

fun CommandSender.sendColoured(message: String) {
    this.sendMessage(message.coloured())
}

fun Player.sendColoured(message: String) {
    this.sendMessage(message.coloured())
}

fun CommandExecutor.setCommand(name: String) {
    getInstance().getCommand(name)!!.setExecutor(this)
}