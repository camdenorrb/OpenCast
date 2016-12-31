package me.camdenorrb.opencast.extensions

import com.google.common.io.Files
import org.bukkit.ChatColor
import java.io.File
import java.io.FileWriter

/**
 * Created by camdenorrb on 12/27/16.
 */

fun String.format(): String = ChatColor.translateAlternateColorCodes('&', this)

inline fun File.write(directory: File = this.parentFile, write: (FileWriter) -> Unit) {
    if (!directory.exists()) Files.createParentDirs(directory)
    FileWriter(this).use { writer -> write(writer) }
}

fun File.readJson(): String {

    if (this.exists().not()) return ""

    val builder = StringBuilder()
    this.reader().useLines { it.forEach { builder.append(it) } }

    return builder.toString()
}