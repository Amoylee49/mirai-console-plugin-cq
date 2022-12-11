package org.example.mirai.plugin.message

import net.mamoe.mirai.message.data.Image
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

//        val imageResource = File(imageUrl).toExternalResource().toAutoCloseable()
//        val image = bot.getFriendOrFail(Constant.DEV_ID).uploadImage(imageResource)

        for (characterHold in allCharacters) {
            for (nickName in characterHold.nickNames) {
                if (nickName.startsWith(rawMessage) || nickName.endsWith(rawMessage)) {
                    val character = characterHold.character
                    return buildMessageChain {
                        +PlainText(character.name)
                        +PlainText("\n")
                        +PlainText(character.pageUrl)
                        +PlainText(character.imageUrl)
                    }
                }
            }
        }
        return null
    }

    private fun isEmpty(str: String?): Boolean {
        return str == null || "" == str
    }

}
