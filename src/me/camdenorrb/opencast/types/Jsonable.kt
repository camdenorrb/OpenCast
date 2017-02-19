package me.camdenorrb.opencast.types

import me.camdenorrb.opencast.extensions.write
import me.camdenorrb.opencast.gson
import java.io.File

/**
 * Created by camdenorrb on 2/16/17.
 */

interface Jsonable {

	fun toJson(): String = gson.toJson(this)

	fun writeJsonTo(file: File) = file.write { it.write(toJson()) }

}

fun <T : Jsonable> readJsonString(from: String, to: Class<T>): T = gson.fromJson(from, to)