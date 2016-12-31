package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import me.camdenorrb.opencast.extensions.write
import org.bukkit.ChatColor.DARK_GREEN
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class ReloadCmd(val openCast: OpenCast) : SubCmd("reload") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        openCast.castHandler.disable()

        openCast.reloadConfig()

        openCast.messagesFile.write { openCast.gson.toJson(openCast.castHandler.messages, it) }

        openCast.loadHandlers()

        openCast.castHandler.enable()

        sender.sendMessage("${DARK_GREEN}Reloaded the config!")

        return true
    }

}