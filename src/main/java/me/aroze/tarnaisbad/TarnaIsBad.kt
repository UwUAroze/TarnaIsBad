package me.aroze.tarnaisbad

import me.aroze.tarnaisbad.command.*
import me.aroze.tarnaisbad.util.setCommand
import me.aroze.tarnaisbad.util.deserializeLocation
import me.aroze.tarnaisbad.listener.PlayerJoinListener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import org.bukkit.Location

class TarnaIsBad : JavaPlugin() {

    companion object {
        fun getInstance() : TarnaIsBad = getPlugin(TarnaIsBad::class.java)
    }

    lateinit var spawn: Location

    override fun onEnable() {
        logger.info("Tarna is bad :sunglasses:")

        saveDefaultConfig()
        spawn = deserializeLocation(config.getString("spawn")!!)

        CraftCommand.setCommand("craft")
        DeleteHomeCommand.setCommand("deletehome")
        DeleteWarpCommand.setCommand("deletewarp")
        EnderchestCommand.setCommand("enderchest")
        HatCommand.setCommand("hat")
        HomeCommand.setCommand("home")
        OpenEnderchestCommand.setCommand("openenderchest")
        PingCommand.setCommand("ping")
        RenameCommand.setCommand("rename")
        SetHomeCommand.setCommand("sethome")
        SetWarpCommand.setCommand("setwarp")
        SpawnCommand.setCommand("spawn")
        WarpCommand.setCommand("warp")

        Bukkit.getPluginManager().registerEvents(PlayerJoinListener, this)
    }
}