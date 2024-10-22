package io.github.japskiddin.threadposter.sample

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.japskiddin.threadposter.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var loadImageTask: LoadImageTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnLoadImage.setOnClickListener { loadImage() }
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        cancelLoadImageTask()
        super.onDestroy()
    }

    private fun cancelLoadImageTask() {
        loadImageTask?.cancel()
        loadImageTask = null
    }

    private fun loadImage() {
        cancelLoadImageTask()
        loadImageTask = LoadImageTask(
            App.threadPoster,
            object : LoadImageTask.TaskListener {
                override fun onStarted() {
                    binding.progress.visibility = View.VISIBLE
                    binding.ivResult.visibility = View.GONE
                    binding.ivResult.setImageBitmap(null)
                }

                override fun onFailed() {
                    Toast.makeText(applicationContext, "Failed to load image", Toast.LENGTH_SHORT).show()
                }

                override fun onCompleted(bitmap: Bitmap) {
                    binding.ivResult.setImageBitmap(bitmap)
                }

                override fun onFinished() {
                    binding.progress.visibility = View.GONE
                    binding.ivResult.visibility = View.VISIBLE
                }
            }
        ).apply {
            start(getRandomImage())
        }
    }

    private fun getRandomImage(): String {
        return IMAGES_LIST.random()
    }

    private companion object {
        val IMAGES_LIST = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFJFlv82Ka95K2YdwY-ysJJWu2S7BanGhVXw&s",
            "https://bestfriends.org/sites/default/files/2023-04/JerryArlyneBenFrechette2915sak.jpg",
            "https://www.cats.org.uk/media/12883/210908ncac104.jpg?width=500&height=333.30078125",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4ZBVltbIEEDTKwVGA2fRX3wW7rT4tR3k_Kw&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcPrFmN5loBnmv5CEWJ6PtBzhrAekTRh7w0Q&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7r0kCHWP3ZrXtB8U8sfTWoE3YXy__m4_nzg&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX63kZF6VlsxTiLH5cBYFMn00zBd-2x7OsbQ&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5H3p6Ir1LHathVgxS96fQbXK0-twVSNdFwQ&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIs-9IrXb1_0htSOE1hUcONujC0CoFeVOctg&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd3FsMmvPJ3IcUOgDrBCMvsJYcSo9UeUbpMQ&s",
            "https://image.petmd.com/files/petmd-kitten-facts.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBi2UnpivXXhTW6uKH2R67lryUl3j2WklzOw&s"
        )
    }
}
