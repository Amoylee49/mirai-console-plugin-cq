package org.example.mirai.plugin.test

import net.mamoe.mirai.message.code.MiraiCode.deserializeMiraiCode
import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.message.data.RichMessage.Key.share

/**
 * 群消息 分享小卡片
 * git issue
 * https://github.com/mamoe/mirai/issues/1428
 */
class ShareTest {
    //JSON 方法介绍 https://juejin.cn/post/6963676982651387935
//        val hashMapOf = hashMapOf("#LINK#" to url, "#TITLE#" to title, "#METADESC#" to content, "#IMG#" to imageUrl)
//        Json.encodeToString(hashMapOf)

    fun shareImageUrl(imageUrl: String?): String {
        val message = share(
            "3url",
            "title4",
            "ggg…",
            "https://"
        )
        var mCodeStr =
            """[mirai:service:1,<?xml version="1.0" encoding="utf-8"?> <msg templateID="12345" action="web" brief="简介 没点进来看见的样子" serviceID="1" url=${imageUrl}><item layout="2"><picture cover="5555555555555"/><title>yyyy</title><summary>描述文字</summary></item><source/></msg>]"""
        println(mCodeStr.deserializeMiraiCode())
        return message.toString()
    }


    fun lightApp(title : String , url : String,dec : String) {
        var json = """ {
                        "app": "com.tencent.miniapp", 
                        "desc": "$dec", 
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
                                    }
                                ], 
                                "title": "$title", 
                                "emphasis_keyword": "今日人品"
                            }
                        }
                    }
                    """
        println(LightApp(json))
    }
}

fun main() {
    println(ShareTest().shareImageUrl("htttpps://///"))
    ShareTest().lightApp("XXX","httpps:///","描述")
}
