package org.example.mirai.plugin.message

import net.mamoe.mirai.message.code.MiraiCode
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import org.example.mirai.plugin.CharacterCachePluginMain


/**
 * 查询 hero 信息
 */
class HeroMessageProcess : MessageProcess() {

    override val command: String = "(^\\s*qr)|(^\\s*查询)"

    override fun process(message: String): MessageChain? {

        val rawMessage = getCommandParameter(message).get(0)
        if (isEmpty(rawMessage))
            return null

        val allCharacters = CharacterCachePluginMain.allCharacters

        for (characterHold in allCharacters) {
            for (nickName in characterHold.nickNames) {
                if (nickName.startsWith(rawMessage) || nickName.endsWith(rawMessage)) {
                    val character = characterHold.character
                    //构建分享模板
//                    return shareByMiraiCode(character.pageUrl, character.name, "", character.imageUrl)
                    //share()方法
//                    return buildMessageChain {
//                        +share(character.pageUrl, character.name, character.content, character.imageUrl)
//                    }
                    return buildMessageChain {
                        +PlainText(character.imageUrl)
                        +PlainText("人物名称："+character.name)
                        +PlainText(character.pageUrl)
                    }
                }
            }
        }
        return null
    }

    private fun shareByMiraiCode(url: String, title: String, content: String, imageUrl: String): MessageChain {
        var json =
            """[mirai:service:1,<?xml version="1.0" encoding="utf-8"?> <msg templateID="12345" action="web" brief="简介 没点进来看见的样子" serviceID="1" url="$url"><item layout="2"><picture cover="$imageUrl"/><title>${title}</title><summary>描述文字</summary></item><source/></msg>\n]"""
        return MiraiCode.deserializeMiraiCode(json)
    }

    private fun isEmpty(str: String?): Boolean {
        return str == null || "" == str
    }

    /**
     * 发送lightApp 提示 发送者版本过低，无法展示内容
     *  https://github.com/mamoe/mirai/issues/1621
     */
    private fun lightApp(title: String, url: String, dec: String): String {
        var json = """ {
                            "app": "com.tencent.miniapp", 
                            "desc": "", 
                            "view": "notification", 
                            "ver": "0.0.0.1", 
                            "prompt": "${title}的今日人品", 
                            "meta": {
                                "notification": {
                                    "appInfo": {
                                        "appName": "Test的今日人品", 
                                        "appType": 4, 
                                        "appid": 1234567890, 
                                        "iconUrl": "http://q1.qlogo.cn/g?b=qq&nk=1234567890&s=640"
                                    }, 
                                    "data": [
                                        {
                                            "title": "今日人品", 
                                            "value": "Test"
                                        }, 
                                        {
                                            "title": "今日适宜", 
                                            "value": "Test"
                                        },
                                        {
                                            "title": "一言", 
                                            "value": "Test --Test"
                                        }
                                    ], 
                                    "title": "", 
                                    "emphasis_keyword": "今日人品"
                                }
                            }
                        }
                    """
        return json
    }
}
