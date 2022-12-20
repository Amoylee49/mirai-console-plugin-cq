package org.example.mirai.plugin.message

import constant.Constant.WIKI_AREA
import constant.Constant.WIKI_PATH
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import java.net.URLEncoder

class NormalMessageProcess : MessageProcess() {
    override val command: String = "(^\\s*qr)|(^\\s*查询)"

    private val specialType = arrayOf(
        "勇士登场期数参考表", "特殊技能", "精淬武器", "戒指", "饰品", "服装", "符文", "女神", "领主", "活动日历"
    )
    private val areaType = arrayOf("讨伐", "挑战", "外传", "试炼", "全力战", "剧情", "竞技", "资源地牢")


    override fun process(message: String): MessageChain? {
        val parameter = getCommandParameter(message).get(0)
        if (parameter == null || "" == parameter)
            return null
        for (type in specialType) {
            if (type.startsWith(parameter) || type.endsWith(parameter))
                return buildMessageChain {
                    +PlainText(type)
                    +PlainText(WIKI_PATH + URLEncoder.encode(type, "utf-8"))
                }
        }
        for (type in areaType) {
            if (type.startsWith(parameter) || type.endsWith(parameter))
                return buildMessageChain {
                    +PlainText(type)
                    +PlainText(WIKI_PATH + URLEncoder.encode(WIKI_AREA, "utf-8"))
                }
        }
        return null
    }

}