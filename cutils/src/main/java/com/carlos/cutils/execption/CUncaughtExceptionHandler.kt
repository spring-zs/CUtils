package com.carlos.cutils.execption

import com.carlos.cutils.util.LogUtils
import kotlin.system.exitProcess

/**
 * Created by Carlos on 2019-10-29.
 */
open class CUncaughtExceptionHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (t == null || e == null) return
        LogUtils.d("find uncaught execption:", e)
        quitApp()
    }

    private fun quitApp() {
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }

}