package me.camdenorrb.opencast

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.camdenorrb.opencast.commands.BroadcastCmd
import me.camdenorrb.opencast.commands.sub.*
import me.camdenorrb.opencast.config.MessagesConfig
import me.camdenorrb.opencast.extensions.format
import me.camdenorrb.opencast.extensions.readJson
import me.camdenorrb.opencast.handlers.CastHandler
import me.camdenorrb.opencast.store.SubCmdStore
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

/**
 * Created by camdenorrb on 12/14/16.
 */

val random = Random()
val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

class OpenCast : JavaPlugin() {

    lateinit var castHandler: CastHandler

    val subCmdStore: SubCmdStore = SubCmdStore()
    val messagesFile = File(dataFolder, "MessagesConfig.json")


    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        subCmdStore.register(AddCmd(this), AnnounceCmd(this), DisableCmd(this), EnableCmd(this), IntervalCmd(this), ListCmd(this), ReloadCmd(this), RemoveCmd(this))

        loadHandlers()

        getCommand("bc").executor = BroadcastCmd(subCmdStore)

    }

	override fun onDisable() {

		castHandler.messages.writeJsonTo(messagesFile)

		castHandler.disable()
		subCmdStore.disable()

		castHandler.unload()
	}


	fun loadHandlers() {

        val messages: MessagesConfig = messagesFile.readJson(MessagesConfig::class.java, { MessagesConfig().apply { writeJsonTo(messagesFile) } })


        castHandler = CastHandler(instance, config.getBoolean("randomMessage", false), config.getBoolean("consoleLogging", false), config.getString("prefix", "&c&lOpenCaster:&a ").format(), messages)

        castHandler.enable()
    }


	companion object {

		lateinit var instance: OpenCast

	}

}