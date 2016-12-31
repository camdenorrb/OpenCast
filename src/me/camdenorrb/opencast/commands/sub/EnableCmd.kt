package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import org.bukkit.ChatColor.DARK_GREEN
import org.bukkit.ChatColor.RED
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class EnableCmd(val openCast: OpenCast) : SubCmd("enable") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        val enabled = openCast.castHandler.enable()
        sender.sendMessage("${if (enabled) DARK_GREEN else RED}Auto broadcasting ${if (enabled) "has been enabled" else "is already enabled"}!")
        return true
    }

}