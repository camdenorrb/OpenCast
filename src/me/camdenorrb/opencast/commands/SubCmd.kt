package me.camdenorrb.opencast.commands

import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

abstract class SubCmd(val name: String, val minArgs: Int = 0, val usage: String? = null) {

    abstract fun execute(sender: CommandSender, args: MutableList<String>): Boolean

}