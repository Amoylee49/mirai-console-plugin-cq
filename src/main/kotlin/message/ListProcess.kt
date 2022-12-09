package org.example.mirai.plugin.message

import java.util.ArrayList

class ListProcess {



    fun listProcess() : List<MessageProcess> {
        val heroMessageProcess = HeroMessageProcess()
        val tipMessageProcess = TipMessageProcess()


        val messageProcess = ArrayList<MessageProcess>()
        messageProcess.add(heroMessageProcess)
        messageProcess.add(tipMessageProcess)
//        messageProcess.addAll(heroMessageProcess,tipMessageProcess)

        return messageProcess;
    }
}