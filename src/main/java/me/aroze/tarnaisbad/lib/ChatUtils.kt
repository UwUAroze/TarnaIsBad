package me.aroze.tarnaisbad.lib

import net.md_5.bungee.api.ChatColor
import java.util.regex.Matcher
import java.util.regex.Pattern

val hexPattern = Pattern.compile("&(#[a-fA-F\\d]{6})");

enum class ChatColors(val hex: String) {
    PRIMARY("&#ffd4e3"),
    SECONDARY("&#ffb5cf"),
    ERROR("&#ff6e6e");
}

fun String.coloured(): String {
    var coloured = this
    var match: Matcher = hexPattern.matcher(coloured
        .replace("&p", ChatColors.PRIMARY.hex)
        .replace("&s", ChatColors.SECONDARY.hex)
        .replace("&g", ChatColors.ERROR.hex))

    while (match.find()) {
        val color: String = coloured.substring(match.start(), match.end())
        coloured = coloured.replace(color, ChatColor.of(color.substring(1)).toString())
        match = hexPattern.matcher(coloured)
    }

    return ChatColor.translateAlternateColorCodes('&', coloured)
}

fun String.undress(): String {
    return ChatColor.stripColor(this)
}

fun String.replaceCaseInsensitive(text: String, replacement: String): String {
    return this.replace(Regex("(?i)$text"), replacement)
}

fun String.handlePluralChar(amount: Int, plural: String = "s"): String {
    return if (amount == 1) this else this + plural
}

fun String.handlePluralWord(amount: Int, plural: String): String {
    return if (amount == 1) this else plural
}