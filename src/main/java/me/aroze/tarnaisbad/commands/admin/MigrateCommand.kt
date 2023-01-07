package me.aroze.tarnaisbad.commands.admin

import me.aroze.tarnaisbad.TarnaIsBad
import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.io.ByteArrayInputStream
import java.io.File

val file = File("plugins/Skript/variables.csv")

// Hex-deserialized binary format:
// 86 05 "world" 80 FF 00 00 00 01 81 04
// "name" 20 80 0C "(world name)" 01
// "x" 09 (8 bytes, float64) 01
// "y" 09 (8 bytes, float64) 01
// "z" 09 (8 bytes, float64) 01
// "pitch" 08 (4 bytes, float32) 03
// "yaw" 08 (4 bytes, float32)

object MigrateCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("migrate", "You don't have permission to do that.")) return true

        Bukkit.getScheduler().runTaskAsynchronously(TarnaIsBad.getInstance(), Runnable {
            file.readLines().forEach {
                val typeRegex = Regex("\"?(home|warp).+")
                if (!it.matches(typeRegex)) return@forEach

                val (type) = typeRegex.find("\"?(home|warp)")!!.destructured

                when (type) {
                    "home" -> {
                        val regex = Regex("\"?home::(([\\da-f]+-?)+)::([^,\"]+), location, ([\\dA-F]+)")
                        val (uuid, _, name, data) = regex.find(it)!!.destructured
                        val reader = ByteArrayInputStream(data.deserializeFromHex())
                        val location = readLocation(reader)

                        SQL.execute(
                            "INSERT INTO homes(name, owner, location) VALUES(?, ?, ?)",
                            name,
                            uuid,
                            serializeLocation(location))
                    }
                    "warp" -> {
                        val regex = Regex("\"?warp::([^:]+)::location, location, ([\\dA-F]+)")
                        val (name, data) = regex.find(it)!!.destructured
                        val reader = ByteArrayInputStream(data.deserializeFromHex())
                        val location = readLocation(reader)

                        SQL.execute(
                            "INSERT INTO warps(name, location) VALUES(?, ?)",
                            name,
                            serializeLocation(location))
                    }
                }
            }

            sender.sendColoured("&pDone migrating homes and warps.")
        })

        return true
    }
}