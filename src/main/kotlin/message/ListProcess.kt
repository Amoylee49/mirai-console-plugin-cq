package org.example.mirai.plugin.message

import java.util.ArrayList

/**
 * 没有@Autowired 一个个实例化
 */
class ListProcess {

    fun listProcess() : List<MessageProcess> {
        val heroMessageProcess = HeroMessageProcess()
        val tipMessageProcess = TipMessageProcess()
        val normalMessageProcess = NormalMessageProcess()

        val messageProcess = ArrayList<MessageProcess>()
        messageProcess.add(heroMessageProcess)
        messageProcess.add(tipMessageProcess)
        messageProcess.add(normalMessageProcess)
//        messageProcess.addAll(heroMessageProcess,tipMessageProcess)

        return messageProcess;
    }
}