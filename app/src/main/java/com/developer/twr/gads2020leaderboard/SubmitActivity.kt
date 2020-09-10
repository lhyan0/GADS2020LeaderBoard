+package com.developer.twr.gads2020leaderboard

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.developer.twr.gads2020leaderboard.databinding.ActivitySubmitBinding
import com.developer.twr.gads2020leaderboard.databinding.DialogueActionBinding

class SubmitActivity: AppCompatActivity() {

    lateinit var binding: ActivitySubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit)

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.button2.setOnClickListener{
            createResponseDialog(R.drawable.ic_check_circle_24, getString(R.string.submission_success))
        }
    }

    private fun createResponseDialog(res: Int, text: String) {
        val dialog = layoutInflater.inflate(R.layout.dialogue_action, null)
        val binding = DialogueActionBinding.bind(dialog)

        val builder: AlertDialog = AlertDialog.Builder(this).create()
        builder.setView(dialog)
        builder.show()

        binding.actionMessage.text = text
        binding.actionImage.setImageResource(res)
    }


}
