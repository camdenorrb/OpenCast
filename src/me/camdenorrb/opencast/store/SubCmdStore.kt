package me.camdenorrb.opencast.store

import me.camdenorrb.opencast.commands.SubCmd
import java.util.*

/**
 * Created by camdenorrb on 12/27/16.
 */

class SubCmdStore {

    val commandSet: HashSet<SubCmd> = HashSet()


    fun disable() { commandSet.clear() }

    fun register(vararg commands: SubCmd) { Collections.addAll(commandSet, *commands) }

    fun byName(name: String): SubCmd? = commandSet.filter { it.name.equals(name, true) }.firstOrNull()

}