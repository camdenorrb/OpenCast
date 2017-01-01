package me.camdenorrb.opencast

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.camdenorrb.opencast.commands.BroadcastCmd
import me.camdenorrb.opencast.commands.sub.*
import me.camdenorrb.opencast.extensions.format
import me.camdenorrb.opencast.extensions.readJson
import me.camdenorrb.opencast.extensions.write
import me.camdenorrb.opencast.handlers.CastHandler
import me.camdenorrb.opencast.store.SubCmdStore
import me.camdenorrb.opencast.wrappers.Messages
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

/**
 * Created by camdenorrb on 12/14/16.
 */

class OpenCast : JavaPlugin() {

    lateinit var castHandler: CastHandler

    val subCmdStore: SubCmdStore = SubCmdStore()
    val messagesFile = File(dataFolder, "Messages.json")
    val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()


    companion object {

        val random = Random()

        lateinit var instance: OpenCast

    }


    override fun onDisable() {

        messagesFile.write { gson.toJson(castHandler.messages, it) }

        castHandler.disable()
        subCmdStore.disable()

        castHandler.unload()
    }

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        subCmdStore.register(AddCmd(this), AnnounceCmd(this), DisableCmd(this), EnableCmd(this), IntervalCmd(this), ListCmd(this), ReloadCmd(this), RemoveCmd(this))

        loadHandlers()


        val broadCastCmd: BroadcastCmd = BroadcastCmd(subCmdStore)

        getCommand("bc").let {
            it.executor = broadCastCmd
            it.tabCompleter = broadCastCmd
        }
    }


    fun loadHandlers() {

        val readJson = messagesFile.readJson()

        val messages: Messages = if (readJson.isEmpty()) Messages() else gson.fromJson(readJson, Messages::class.java)


        castHandler = CastHandler(instance, config.getBoolean("randomMessage", false), config.getBoolean("consoleLogging", false), config.getString("prefix", "&c&lOpenCaster:&a ").format(), messages)

        castHandler.enable()
    }

}