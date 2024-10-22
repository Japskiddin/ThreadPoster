package io.github.japskiddin.threadposter.sample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.WorkerThread
import io.github.japskiddin.threadposter.ThreadPoster
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class LoadImageTask(
    private val threadPoster: ThreadPoster,
    private var listener: TaskListener?
) {
    interface TaskListener {
        fun onStarted()

        fun onFailed()

        fun onCompleted(bitmap: Bitmap)

        fun onFinished()
    }

    private var url: String = ""

    fun cancel() {
        listener = null
    }

    fun start(url: String) {
        this.url = url
        threadPoster.postToBackground { startInternal() }
    }

    @WorkerThread
    private fun startInternal() {
        threadPoster.postToUI { listener?.onStarted() }
        val bitmap = loadImage(url)
        if (bitmap != null) {
            threadPoster.postToUI { listener?.onCompleted(bitmap) }
        } else {
            threadPoster.postToUI { listener?.onFailed() }
        }
        threadPoster.postToUI { listener?.onFinished() }
    }

    private fun loadImage(link: String): Bitmap? {
        val url = URL(link)
        var connection: HttpURLConnection? = null
        return try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val bufferedInputStream = BufferedInputStream(connection.inputStream)
            BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            null
        } finally {
            connection?.disconnect()
        }
    }
}
