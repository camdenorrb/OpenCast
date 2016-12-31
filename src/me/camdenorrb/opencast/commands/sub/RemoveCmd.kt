package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import org.bukkit.ChatColor.*
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class RemoveCmd(val openCast: OpenCast) : SubCmd("remove", 1, "$RED/Bc Remove <Index>") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {

        val messages = openCast.castHandler.messages

        val index = try { args[0].toInt() } catch (ex: Exception) { return false }

        if (index < 0 || (messages.messageList.size - 1) < index) return false
        val removed = messages.messageList.removeAt(index)

        sender.sendMessage("$AQUA\"$LIGHT_PURPLE$removed$AQUA\" was removed from the list!")
        return true
    }

}