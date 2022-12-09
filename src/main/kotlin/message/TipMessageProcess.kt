package org.example.mirai.plugin.message

import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

class TipMessageProcess : MessageProcess() {
    override val command: String = "(^\\s*tip)|(^\\s*命令)"

    override fun process(message: String): MessageChain {
        val buildMessageChain = buildMessageChain {
            +PlainText(
                "\"支持勇士，勇士登场期数参考表，特殊技能，精淬武器，戒指，服装，符文，女神，领主，活动日历等wiki查询。\\n\" +\n" +
                    "\"请输入例如qr浦西或者查询浦西\""
            )
//                +AtAll
//                +Image("/f8f1ab55-bf8e-4236-b55e-955848d7069f")
//                add(At(123456)) // `+` 和 `add` 作用相同
        }
        return buildMessageChain;
    }


}