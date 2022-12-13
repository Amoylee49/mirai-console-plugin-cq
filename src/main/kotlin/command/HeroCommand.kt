package org.example.mirai.plugin.command

import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import org.example.mirai.plugin.PluginMain

object HeroCommand : RawCommand(
//    /login 2664431920 jsan520. IPAD
    PluginMain, "qr", // 使用插件主类对象作为指令拥有者；设置主指令名为 "name"
    // 可选：
    "查询", "we", // 增加两个次要名称
    usage = "/qr arg1 arg2", // 设置用法，将会在 /help 展示
    description = "#这是一个查询hero指令", // 设置描述，将会在 /help 展示
    prefixOptional = true, // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
) {

    override suspend fun CommandContext.onCommand(args: MessageChain) {

        val chain = buildMessageChain {
            +PlainText("https://patchwiki.biligame.com/resources/assets/images/logo/logo_cq.png")
            +PlainText("四天王 夏洛特 - WIKI game ...")

//            add(At(123456)) // `+` 和 `add` 作用相同
        }
        val serializeToMiraiCode = chain.serializeToMiraiCode()
        val lightApp = LightApp(chain.serializeToMiraiCode())
        sender.sendMessage(lightApp)
//       val userId = event.sender.id
//       val groupId = event.sender.group.id
//        if (event.message.contentToString().startsWith("hi"))
//            event.group.sendMessage("hi to you")
//
//        val lightApp = LightApp(chain.serializeToMiraiCode())
//        event.group.sendMessage(lightApp)
    }


//    JsonMessage(json)
//    LightApp(json)
//    这是两种json, 要试一试你的json是哪一种
}