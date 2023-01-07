package me.aroze.tarnaisbad.lib

import org.bukkit.Bukkit
import org.bukkit.Location
import java.io.InputStream
import java.nio.ByteBuffer

fun serializeLocation(location: Location): String {
    return "${location.x}, ${location.y}, ${location.z}, ${location.world!!.name}, ${location.yaw}, ${location.pitch}"
}

fun deserializeLocation(input: String): Location {
    val chunks = input.split(", ")

    val x = chunks[0].toDouble()
    val y = chunks[1].toDouble()
    val z = chunks[2].toDouble()
    val world = Bukkit.getWorld(chunks[3])!!
    val yaw = chunks[4].toFloat()
    val pitch = chunks[5].toFloat()

    return Location(world, x, y, z, yaw, pitch)
}

fun readLocation(stream: InputStream): Location {
    stream.skipNBytes(22)

    val world = readString(stream)
    stream.skipNBytes(2)

    val x = readDouble(stream)
    stream.skipNBytes(3)

    val y = readDouble(stream)
    stream.skipNBytes(3)

    val z = readDouble(stream)
    stream.skipNBytes(7)

    val pitch = readFloat(stream)
    stream.skipNBytes(5)

    val yaw = readFloat(stream)

    return Location(Bukkit.getWorld(world), x, y, z, yaw, pitch)
}

fun readString(stream: InputStream): String {
    val builder = StringBuilder()
    while (true) {
        val byte = stream.read()

        if (byte == 1)
            break

        builder.append(byte.toChar())
    }

    return builder.toString()
}

fun readFloat(stream: InputStream): Float {
    val bytes = stream.readNBytes(4)
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.float
}

fun readDouble(stream: InputStream): Double {
    val bytes = stream.readNBytes(8)
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.double
}

// https://stackoverflow.com/questions/66613717/kotlin-convert-hex-string-to-bytearray
fun String.deserializeFromHex(): ByteArray {
    check(length % 2 == 0) { "Must have an even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}