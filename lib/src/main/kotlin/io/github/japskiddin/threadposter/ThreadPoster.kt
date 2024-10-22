package io.github.japskiddin.threadposter

public class ThreadPoster {
    private val backgroundThreadPoster: IThreadPoster = BackgroundThreadPoster()
    private val uiThreadPoster: IThreadPoster = UiThreadPoster()

    public fun postToUI(runnable: Runnable) {
        uiThreadPoster.post(runnable)
    }

    public fun postToBackground(runnable: Runnable) {
        backgroundThreadPoster.post(runnable)
    }
}
