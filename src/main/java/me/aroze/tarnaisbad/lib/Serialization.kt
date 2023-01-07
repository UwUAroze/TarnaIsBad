package me.aroze.tarnaisbad.util

import org.bukkit.Bukkit
import org.bukkit.Location

fun serializeLocation(location: Location): String {
    return "${location.x},${location.y},${location.z},${location.world!!.name},${location.yaw},${location.pitch}"
}

fun deserializeLocation(input: String): Location {
    val chunks = input.split(",")

    val x = chunks[0].toDouble()
    val y = chunks[1].toDouble()
    val z = chunks[2].toDouble()
    val world = Bukkit.getWorld(chunks[3])!!
    val yaw = chunks[4].toFloat()
    val pitch = chunks[5].toFloat()

    return Location(world, x, y, z, yaw, pitch)
}