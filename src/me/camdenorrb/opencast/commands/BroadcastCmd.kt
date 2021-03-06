package me.camdenorrb.opencast.commands

import me.camdenorrb.opencast.store.SubCmdStore
import org.bukkit.ChatColor.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

/**
 * Created by camdenorrb on 12/18/16.
 */
class BroadcastCmd(val cmdStore: SubCmdStore) : TabExecutor {

    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<out String>) = if (args.isEmpty()) commands else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (args.isEmpty()) return { sender.sendMessage(help); true }()


        val argsList = args.toMutableList()

        val command = cmdStore.byName(argsList.removeAt(0)) ?: return { sender.sendMessage(help); true }()

        if (argsList.size < command.minArgs || !command.execute(sender, argsList)) command.usage?.let { sender.sendMessage(it) }

        return true
    }


    companion object {

        val commands = arrayListOf("announce", "list", "reload", "add", "remove", "enable", "disable", "interval")

        val help: String = """$AQUA${BOLD}Open$RESET${BOLD}Caster$RESET
    $AQUA/bc $DARK_GRAY- ${GRAY}Show Commands.
    $AQUA/bc list $DARK_GRAY- ${GRAY}List currently set messages.
    $AQUA/bc announce $GREEN<Message> $DARK_GRAY- ${GRAY}Broadcasts a message to the whole server.
    $AQUA/bc reload $DARK_GRAY- ${GRAY}Reload the config.\n" +
    $AQUA/bc add $GREEN<Message> $DARK_GRAY- ${GRAY}Add an auto broadcast message from the list.
    $AQUA/bc remove $GREEN<ID> (Each message get an ID such as 1, 2 etc..) $DARK_GRAY- ${GRAY}Remove an auto broadcast message from the list.
    $AQUA/bc enable $DARK_GRAY- ${GRAY}Enables Auto Broadcaster.
    $AQUA/bc disable $DARK_GRAY- ${GRAY}Disables the Auto Broadcaster.
    $AQUA/bc interval $GREEN<#> $DARK_GRAY- ${GRAY}Set an interval between auto broadcast (Seconds)"""

    }

}