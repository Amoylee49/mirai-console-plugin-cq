package org.example.mirai.plugin.command

import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.CompositeCommand
import org.example.mirai.plugin.PluginMain

/**
 * 子指令可以拥有多个名称，即
 * @SubCommand("child1", "child2")
 * 可以由 /main child1 或 /main child2 执行。
 */
object NormalCommand : CompositeCommand(PluginMain, "main") {
    // ...

    @SubCommand("qr")
    suspend fun foo(context: CommandContext, arg: String) {
        println(arg)
    }
}