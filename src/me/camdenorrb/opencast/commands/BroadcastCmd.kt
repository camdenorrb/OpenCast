package me.camdenorrb.opencast.commands

import me.camdenorrb.opencast.store.SubCmdStore
import org.bukkit.ChatColor.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * Created by camdenorrb on 12/18/16.
 */
class BroadcastCmd(val cmdStore: SubCmdStore) : CommandExecutor, TabCompleter {

    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<out String>) = if (args.isEmpty()) commands else null

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (args.isEmpty()) return { sender.sendMessage(help); true }()

        val argsList = mutableListOf(*args)
        val command = cmdStore.byName(argsList.removeAt(0))?: return { sender.sendMessage(help); true }()


        if (argsList.size < command.minArgs || !command.execute(sender, argsList)) command.usage?.let { sender.sendMessage(it) }

        return true
    }


    companion object {

        val commands = arrayListOf("announce", "list", "reload", "add", "remove", "enable", "disable", "interval")

        val help: String = "$AQUA${BOLD}Open$RESET${BOLD}Caster$RESET\n" +
                "   $AQUA/bc $DARK_GRAY- ${GRAY}Show Commands.\n" +
                "   $AQUA/bc announce $GREEN<Message> $DARK_GRAY- ${GRAY}Broadcasts a message to the whole server\n" +
                "   $AQUA/bc reload $DARK_GRAY- ${GRAY}Reloads the Plugin.\n" +
                "   $AQUA/bc add $GREEN<Message> $DARK_GRAY- ${GRAY}Add an auto broadcast message from the list.\n" +
                "   $AQUA/bc remove $GREEN<ID> (Each message get an ID such as 1, 2 etc..) $DARK_GRAY- ${GRAY}Remove an auto broadcast message from the list.\n" +
                "   $AQUA/bc enable $DARK_GRAY- ${GRAY}Enables Auto Broadcaster.\n" +
                "   $AQUA/bc disable $DARK_GRAY- ${GRAY}Disables the Auto Broadcaster.\n" +
                "   $AQUA/bc interval $GREEN<#> $DARK_GRAY- ${GRAY}Set an interval between auto broadcast (Seconds)\n"

    }

}