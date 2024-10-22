package io.github.japskiddin.threadposter

import android.os.Handler
import android.os.Looper

internal class UiThreadPoster : IThreadPoster {
    private val uiHandler: Handler = Handler(Looper.getMainLooper())

    override fun post(runnable: Runnable) {
        uiHandler.post(runnable)
    }
}
