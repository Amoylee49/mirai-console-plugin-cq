package org.example.mirai.plugin

import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.EventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
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
                    val messChain = messageProcess.process(rawMessage)
                    if (messChain != null) {
//                        val imageUrl = messChain[0].contentToString()
                        //② 先上传图片，获得 Image (报错，g)
                        /* group.uploadImage(URL(imageUrl).openStream().toExternalResource()).also {
                             // ② 和其他类型消息一起发送
                             group.sendMessage(buildMessageChain {
                                 val add = add(PlainText("plain"))
                                 for (singleMess in messChain.drop(1)) { // i in [1, 10), 不包含 10
                                     add(singleMess)
                                 }
                                 add(it)
                             })*/
                        // ② 直接发送
                        group.sendMessage(messChain)

                    }
                }
            }
            //不继续处理
            return@subscribeAlways
        }

        /* 发送image参考：
        //如何发送图片： https://github.com/mamoe/mirai/discussions/864
         eventChannel.subscribeAlways<GroupMessageEvent> {
             //① 将文件转为 ExternalResource，指定格式或不指定
             group.sendImage(File("jpg_image_path").toExternalResource("jpg"))
             //② 先上传图片，获得 Image
             group.uploadImage(File("png_image_path").toExternalResource("png")).also {
                 // ② 和其他类型消息一起发送
                 group.sendMessage(buildMessageChain {
                     add(PlainText("plain"))
                     add(it)
                 })
                 // ② 直接发送
                 group.sendMessage(it)
             }
             //直接传入 File 和 图片格式
             group.sendImage(File("img_path"), "bmp")
             //直接传入 InputStream 和 图片格式
             group.sendImage(File("file").inputStream(), "gif")
         }*/


    }
}


