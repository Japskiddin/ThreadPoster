package io.github.japskiddin.threadposter.sample

import android.app.Application
import io.github.japskiddin.threadposter.ThreadPoster

class App : Application() {
    companion object {
        val threadPoster: ThreadPoster = ThreadPoster()
    }
}
