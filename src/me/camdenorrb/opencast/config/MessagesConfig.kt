package me.camdenorrb.opencast.config

import me.camdenorrb.opencast.types.Jsonable

/**
 * Created by camdenorrb on 12/30/16.
 */

data class MessagesConfig(var delay: Long = 180, val messageList: MutableList<String> = mutableListOf("&aThis is the default message for OpenCast.")) : Jsonable