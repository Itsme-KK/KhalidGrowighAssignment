package com.example.growighassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen1)

        findViewById<TextView>(R.id.btn_skip_screen1).setOnClickListener { startHomeActivity() }

        findViewById<ImageView>(R.id.btn_next_screen1).setOnClickListener { showNextScreen() }

    }

    private fun showNextScreen() {
        when (currentScreen) {
            1 -> {
                setContentView(R.layout.onboarding_screen2)
                findViewById<TextView>(R.id.btn_skip_screen2).setOnClickListener {
                    startHomeActivity()
                }
                findViewById<ImageView>(R.id.btn_next_screen2).setOnClickListener {
                    showNextScreen()
                }
                currentScreen++
            }

            2 -> {
                setContentView(R.layout.onboarding_screen3)
                findViewById<TextView>(R.id.btn_skip_screen3).setOnClickListener {
                    startHomeActivity()
                }
                findViewById<TextView>(R.id.btn_next_screen3).setOnClickListener {
                    startHomeActivity()
                }
                currentScreen++
            }
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    companion object {
        private var currentScreen = 1
    }
}