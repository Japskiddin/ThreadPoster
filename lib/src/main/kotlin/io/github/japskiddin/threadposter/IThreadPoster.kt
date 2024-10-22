package io.github.japskiddin.threadposter

internal interface IThreadPoster {
    fun post(runnable: Runnable)
}
