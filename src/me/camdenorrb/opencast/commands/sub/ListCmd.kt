package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import me.camdenorrb.opencast.extensions.format
import org.bukkit.ChatColor.*
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/30/16.
 */

class ListCmd(val openCast: OpenCast) : SubCmd("list") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {

        val builder = StringBuilder("${AQUA}MessagesConfig currently loaded in:\n")

        openCast.castHandler.messages.messageList.forEachIndexed({i, string -> builder.append("   $GRAY- $GREEN$i: $LIGHT_PURPLE${string.format()}\n") })

        sender.sendMessage(builder.toString())

        return true
    }
}