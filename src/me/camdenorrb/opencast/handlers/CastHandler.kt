package me.camdenorrb.opencast.handlers

import me.camdenorrb.opencast.OpenCast
import me.camdenorrb.opencast.extensions.format
import me.camdenorrb.opencast.wrappers.Messages
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import java.util.logging.Level

/**
 * Created by camdenorrb on 12/27/16.
 */

class CastHandler(val plugin: JavaPlugin, var randomMessages: Boolean, var consoleLogging: Boolean, var prefix: String, var messages: Messages) {

    var task: BukkitTask? = null
    var isEnabled: Boolean = false


    fun unload() { messages.messageList.clear() }

    fun disable(): Boolean {
        if (isEnabled.not()) return false

        isEnabled = false

        task?.let { plugin.server.scheduler.cancelTask(it.taskId) }

        return true
    }

    fun enable(): Boolean {
        if (isEnabled) return false

        isEnabled = true

        startBroadcasting()

        return true
    }

    private fun startBroadcasting() {

        var position: Int = 0

        val delay = messages.delay * 20


        task = plugin.server.scheduler.runTaskTimerAsynchronously(plugin, {

            val size = messages.messageList.size
            if (size == 0) return@runTaskTimerAsynchronously

            if(randomMessages) {
                var randomNumber = OpenCast.random.nextInt(size - 1)
                while (position == randomNumber) randomNumber = OpenCast.random.nextInt(size - 1)
                position = randomNumber

            } else if (++position >= size) position = 0


            "$prefix${messages.messageList[position]}".format().let {
                if (consoleLogging) plugin.logger.log(Level.INFO, it)
                plugin.server.onlinePlayers.forEach { player -> player.sendMessage(it) }
            }

        }, delay, delay)

    }

}