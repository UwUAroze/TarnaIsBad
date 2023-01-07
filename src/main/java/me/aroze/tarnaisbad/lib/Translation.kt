package me.aroze.tarnaisbad.lib

import me.aroze.tarnaisbad.TarnaIsBad

fun translate(key: String): String {
	val messages = TarnaIsBad.getInstance().config.getConfigurationSection("messages")
		?: throw IllegalStateException("Couldn't find 'messages' section in config.yml")

	return messages.getString(key)
		?: throw IllegalStateException("Couldn't find 'messages.$key' key in config.yml")
}