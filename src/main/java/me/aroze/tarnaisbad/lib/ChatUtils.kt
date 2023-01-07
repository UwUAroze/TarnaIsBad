package me.aroze.tarnaisbad.lib

import net.md_5.bungee.api.ChatColor
import java.util.regex.Matcher
import java.util.regex.Pattern

val hexPattern = Pattern.compile("&(#[a-fA-F\\d]{6})");

enum class ChatColors(val hex: String) {
    PRIMARY(translate("colors.primary")),
    SECONDARY(translate("colors.secondary")),
    ERROR(translate("colors.error"));
}

fun String.coloured(): String {
    var coloured = this
        .replace("&p", ChatColors.PRIMARY.hex)
        .replace("&s", ChatColors.SECONDARY.hex)
        .replace("&g", ChatColors.ERROR.hex)
    var match: Matcher = hexPattern.matcher(coloured)

    while (match.find()) {
        val color: String = coloured.substring(match.start(), match.end())
        coloured = coloured.replace(color, ChatColor.of(color.substring(1)).toString())
        match = hexPattern.matcher(coloured)
    }

    return ChatColor.translateAlternateColorCodes('&', coloured)
}