package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import me.camdenorrb.opencast.extensions.format
import org.bukkit.ChatColor.*
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class AddCmd(val openCast: OpenCast) : SubCmd("add", 1, "$RED/Bc Add <Message>") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        val message = args.joinToString(" ")

        openCast.castHandler.messages.messageList.add(message)

        sender.sendMessage("$AQUA\"$LIGHT_PURPLE${message.format()}$AQUA\" was added to the list!")
        return true
    }

}
