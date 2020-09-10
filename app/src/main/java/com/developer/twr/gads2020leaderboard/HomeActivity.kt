package com.developer.twr.gads2020leaderboard

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.developer.twr.gads2020leaderboard.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(){

   lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager);
        binding.viewPager.setAdapter(viewPagerAdapter)
        binding.tab.setupWithViewPager(binding.viewPager);

        binding.submitButton.setOnClickListener{
            startActivity(Intent(this, SubmitActivity::class.java))
        }
    }

}