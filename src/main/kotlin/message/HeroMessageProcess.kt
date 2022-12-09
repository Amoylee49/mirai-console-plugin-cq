package org.example.mirai.plugin.message

import bean.Character
import method.CharacterCaches
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

/**
 * 查询 hero 信息
 */
class HeroMessageProcess : MessageProcess() {

    override val command: String = "(^\\s*qr)|(^\\s*查询)"


    override fun process(message: String): MessageChain {

        val rawMessage = getCommandParameter(message).get(0)

        val allCharacters = CharacterCaches().allCharacters

        var character = Character()
        for (characterHold in allCharacters) {
            for (nickName in characterHold.nickNames) {
                if (nickName.startsWith(rawMessage) || nickName.endsWith(rawMessage)) {
                    character = characterHold.character

                }
            }
        }
        val buildMessageChain = buildMessageChain {
            +PlainText(character.name)
        }
        return buildMessageChain
    }

}