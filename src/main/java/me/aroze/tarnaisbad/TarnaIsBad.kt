package me.aroze.tarnaisbad

import me.aroze.tarnaisbad.commands.admin.MigrateCommand
import me.aroze.tarnaisbad.commands.homes.DeleteHomeCommand
import me.aroze.tarnaisbad.commands.homes.HomeCommand
import me.aroze.tarnaisbad.commands.homes.HomesCommand
import me.aroze.tarnaisbad.commands.homes.SetHomeCommand
import me.aroze.tarnaisbad.commands.warps.DeleteWarpCommand
import me.aroze.tarnaisbad.commands.warps.SetWarpCommand
import me.aroze.tarnaisbad.commands.warps.WarpCommand
import me.aroze.tarnaisbad.commands.warps.WarpsCommand
import me.aroze.tarnaisbad.lib.SQL
import me.aroze.tarnaisbad.lib.setCommand
import me.aroze.tarnaisbad.listeners.TabCompleteListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TarnaIsBad : JavaPlugin() {

    companion object {
        fun getInstance() : TarnaIsBad = getPlugin(TarnaIsBad::class.java)
    }

    override fun onEnable() {
        logger.info("Tarna is cool :flushed: (don't tell ollie!)")

        saveDefaultConfig()

        DeleteHomeCommand.setCommand("deletehome")
        DeleteWarpCommand.setCommand("deletewarp")
        HomeCommand.setCommand("home")
        SetHomeCommand.setCommand("sethome")
        SetWarpCommand.setCommand("setwarp")
        WarpCommand.setCommand("warp")
        HomesCommand.setCommand("homes")
        WarpsCommand.setCommand("warps")
        MigrateCommand.setCommand("migrate")

        Bukkit.getPluginManager().registerEvents(TabCompleteListener, this)
    }

    override fun onDisable() {
        SQL.close()
    }
}