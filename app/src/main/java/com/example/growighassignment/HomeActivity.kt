package com.example.growighassignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.growighassignment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFeed?.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

        binding?.btnUpload?.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }
    }
}
