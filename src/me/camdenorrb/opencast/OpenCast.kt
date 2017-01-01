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

/**
 * Created by camdenorrb on 12/14/16.
 */

class OpenCast : JavaPlugin() {

    lateinit var castHandler: CastHandler

    val subCmdStore: SubCmdStore = SubCmdStore()
    val messagesFile = File(dataFolder, "Messages.json")
    val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()


    companion object { var instance: OpenCast? = null }


    override fun onDisable() {
        instance = null

        messagesFile.write { gson.toJson(castHandler.messages, it) }

        castHandler.disable()
        subCmdStore.disable()

        castHandler.unload()
    }

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        subCmdStore.register(AddCmd(instance!!), AnnounceCmd(instance!!), DisableCmd(instance!!), EnableCmd(instance!!), IntervalCmd(instance!!), ListCmd(instance!!), ReloadCmd(instance!!), RemoveCmd(instance!!))

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


        castHandler = CastHandler(instance!!, config.getBoolean("consoleLogging", false), config.getString("prefix", "&c&lOpenCaster:&a ").format(), messages)

        castHandler.enable()
    }

}