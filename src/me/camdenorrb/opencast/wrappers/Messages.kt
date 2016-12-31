package me.camdenorrb.opencast.wrappers

/**
 * Created by camdenorrb on 12/30/16.
 */

data class Messages(var delay: Long = 180, val messageList: MutableList<String> = mutableListOf("&aThis is the default message for OpenCast."))