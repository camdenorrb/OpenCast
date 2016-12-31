package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import org.bukkit.ChatColor.DARK_GREEN
import org.bukkit.ChatColor.RED
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class DisableCmd(val openCast: OpenCast) : SubCmd("disable") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        val disabled = openCast.castHandler.disable()
        sender.sendMessage("${if (disabled) DARK_GREEN else RED}Auto broadcasting ${if (disabled) "has been disabled" else "is already disabled"}!")
        return true
    }

}