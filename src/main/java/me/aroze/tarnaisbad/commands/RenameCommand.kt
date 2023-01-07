package me.aroze.tarnaisbad.commands

import me.aroze.tarnaisbad.util.coloured
import me.aroze.tarnaisbad.util.isStupid
import me.aroze.tarnaisbad.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object RenameCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return sender.isStupid("You aren't a player!")
        if (args.isEmpty()) return sender.isStupid("You forgot to specify the renamed name. Dementia much?")

        val rename = args.joinToString(" ")
        val item = sender.inventory.itemInMainHand

        if (rename.length > 100) return sender.isStupid("To prevent your shenanigans, there is a character limit of 100")
        if (item.type.isAir) return sender.isStupid("Hold an item dumbass")

        val meta = item.itemMeta!!
        meta.setDisplayName(rename.coloured())
        item.itemMeta = meta

        return sender.sendFinalColoured("&#ffd4e3I've renamed your held item, you lazy bitch. :D")

    }

}