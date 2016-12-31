package me.camdenorrb.opencast.commands.sub

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.commands.SubCmd
import org.bukkit.ChatColor.*
import org.bukkit.command.CommandSender

/**
 * Created by camdenorrb on 12/27/16.
 */

class IntervalCmd(val openCast: OpenCast) : SubCmd("interval", 1, "$RED/Interval <Seconds>") {

    override fun execute(sender: CommandSender, args: MutableList<String>): Boolean {
        val delay: Long = try { args[0].toLong() } catch(e: Exception) { return false }
        if (delay < 0) return false

        openCast.castHandler.messages.delay = delay

        openCast.castHandler.disable()
        openCast.castHandler.enable()

        sender.sendMessage("${DARK_GREEN}Set the delay to $LIGHT_PURPLE$delay ${DARK_GREEN}Seconds!")

        return true
    }

}