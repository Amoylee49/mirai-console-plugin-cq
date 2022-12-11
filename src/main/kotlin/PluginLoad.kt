package org.example.mirai.plugin

import net.mamoe.mirai.console.util.ContactUtils.getContact
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.EventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.MessageChain.Companion.serializeToJsonString
import org.example.mirai.plugin.message.ListProcess
import org.example.mirai.plugin.message.MessageProcess
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//@Slf4j
class PluginLoad {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun pluginLoad(eventChannel: EventChannel<Event>) {
        log.info("++++++++++++++++ PluginLoad 加载成功 +++++++++++")
        val list = ArrayList<MessageProcess>();
        val listProcess = ListProcess().listProcess()
        //配置文件目录 "${dataFolder.absolutePath}/"
//        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            //群消息
            val rawMessage = message.contentToString()
            for (messageProcess in listProcess) {
                if (messageProcess.isMatch(rawMessage)) {
                    val process = messageProcess.process(rawMessage)
                    if (process != null) {
                        val serializeToMiraiCode = process.serializeToMiraiCode()
                        var lightApp = LightApp(process.serializeToJsonString())
                        group.sendMessage(process)
                        bot.getContact(group.id).sendMessage(lightApp)
                    }
                }

                //不继续处理
                return@subscribeAlways
//                bot.getContact(sender.id).uploadImage()
                /*if (message.contentToString().startsWith("复读")) {
                    group.sendMessage(message.contentToString().replaceFirst("复读", "复制ddd"))
                }
                if (message.contentToString() == "hi") {
                    group
                    //群内发送
                    group.sendMessage("hi")
                    //向发送者私聊发送消息
                    sender.sendMessage("hi")
                    //不继续处理
                    return@subscribeAlways
                }
                //分类示例
                message.forEach {
                    //循环每个元素在消息里
                    if (it is Image) {
                        //如果消息这一部分是图片
                        val url = it.queryUrl()
                        group.sendMessage("图片，下载地址$url")
                    }
//                if (it is PlainText) {
//                    //如果消息这一部分是纯文本
//                    group.sendMessage("纯文本，内容:${it.content}")
//                }
                }*/
            }
        }
    }
}
