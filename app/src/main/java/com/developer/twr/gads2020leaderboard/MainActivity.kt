package com.developer.twr.gads2020leaderboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        handler.postDelayed({
             run() {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }




}
