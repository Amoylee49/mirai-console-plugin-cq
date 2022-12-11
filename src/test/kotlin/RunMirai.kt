package org.example.mirai.plugin

import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CommandOwner
import net.mamoe.mirai.console.plugin.PluginManager
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import org.example.mirai.plugin.command.HeroCommand

suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

    //如果是Kotlin
    PluginMain.load()
    PluginMain.enable()
    //如果是Java
//    JavaPluginMain.INSTANCE.load()
    CharacterCachePluginMain.INSTANCE.enable()

    val bot = MiraiConsole.addBot(2664431920, "jsan520.") {
        fileBasedDeviceInfo()

//         或
//        fileBasedDeviceInfo("myDeviceInfo.json") // 存储为 "myDeviceInfo.json"
    }.alsoLogin()
//    CommandManager.registerCommand(HeroCommand())
//    CommandManager.getRegisteredCommands(CommandOwner())
//    PluginManager.INSTANCE.getAllRegisteredCommands()
    MiraiConsole.job.join()
}