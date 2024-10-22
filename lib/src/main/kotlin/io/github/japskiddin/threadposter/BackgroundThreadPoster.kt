package io.github.japskiddin.threadposter

import android.os.Build
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

internal class BackgroundThreadPoster : IThreadPoster {
    private val executorService: ExecutorService =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Executors.newWorkStealingPool()
        } else {
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        }

    override fun post(runnable: Runnable) {
        executorService.execute(runnable)
    }
}
