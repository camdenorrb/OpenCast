package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import org.bukkit.ChatColor.RED
import org.bukkit.command.CommandSender
import java.util.logging.Level

/**
 * Created by camdenorrb on 12/27/16.
 */

class AnnounceCmd(val openCast: OpenCast) : SubCmd("announce", 1, "$RED/Bc Announce <Message>") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        val message = "${openCast.castHandler.prefix}${args.joinToString(" ")}"

        openCast.server.onlinePlayers.forEach { it.sendMessage(message) }

        if (openCast.castHandler.consoleLogging) openCast.logger.log(Level.INFO, message)

        return true
    }

}