package me.camdenorrb.opencast.extensions

import com.google.common.io.Files
import me.camdenorrb.opencast.gson
import me.camdenorrb.opencast.types.Jsonable
import org.bukkit.ChatColor
import java.io.File
import java.io.FileReader
import java.io.FileWriter

/**
 * Created by camdenorrb on 12/27/16.
 */

fun String.format(): String = ChatColor.translateAlternateColorCodes('&', this)


inline fun <R> File.read(reader: (FileReader) -> R): R = FileReader(this).use{ return reader(it) }

inline fun File.write(directory: File = this.parentFile, write: (FileWriter) -> Unit) {
    if (!directory.exists()) Files.createParentDirs(directory)
    FileWriter(this).use { writer -> write(writer) }
}

inline fun <T : Jsonable> File.readJson(to: Class<T>, onDoesNotExist: () -> T): T {
    if (this.exists().not()) return onDoesNotExist()
    return this.read { gson.fromJson(it, to) }
}