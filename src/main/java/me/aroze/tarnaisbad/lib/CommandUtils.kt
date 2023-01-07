package me.aroze.tarnaisbad.lib

import me.aroze.tarnaisbad.TarnaIsBad.Companion.getInstance
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Used for incorrect usage/etc. Generic error message.
fun CommandSender.sendWarning(message: String) : Boolean {
    this.sendColoured("&c⚠ $message")
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