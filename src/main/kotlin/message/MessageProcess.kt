package org.example.mirai.plugin.message

import net.mamoe.mirai.message.data.MessageChain
import java.util.regex.Pattern

abstract class MessageProcess() {

    abstract val command: String

     open fun isMatch(message: String): Boolean {
        val matcher = Pattern.compile(command, Pattern.CASE_INSENSITIVE)
            .matcher(message)
        return matcher.find()
      }

    fun getCommandParameter(message: String): List<String> {
        val matcher = Pattern.compile(command, Pattern.CASE_INSENSITIVE)
            .matcher(message)
        val trim = matcher.replaceFirst("").trim()
        return trim.split(",")
    }

    abstract fun process(message: String): MessageChain?
}