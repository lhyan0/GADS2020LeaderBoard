package com.developer.twr.gads2020leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.developer.twr.gads2020leaderboard.databinding.ActivitySubmitBinding
import com.developer.twr.gads2020leaderboard.databinding.DialogueActionBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialogue_action.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubmitActivity: AppCompatActivity() {

    private val retrofit = Retrofit.Builder().baseUrl(Post_Base_Url).addConverterFactory(
        GsonConverterFactory.create()).build()
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
           showConfirmationDialog()
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

    private fun sendData(firstName:String, lastName:String, email:String, projectLink:String){

        retrofit.create(LeaderBoardApi::class.java).also {
            it.projectSubmit(firstName,lastName,email,projectLink, "AAD").enqueue(object :
                Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    showSuccessDialog()
                    Toast.makeText(applicationContext,"Data Posted",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    showFailureDialog()
                    Toast.makeText(applicationContext,"Data not Posted", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
    private fun showConfirmationDialog() {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)

        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialogue_query, viewGroup, false)

        val yes : Button = dialogView.findViewById(R.id.query_continue)
        val close : ImageView = dialogView.findViewById(R.id.query_cancel)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()

        close.setOnClickListener {
            alertDialog.dismiss()
        }

        yes.setOnClickListener {
            sendData(binding.enterFirstName.text.toString(),binding.enterLastName.text.toString(),binding.enterEmailAddress.text.toString(),binding.enterProjectLink.text.toString())
            alertDialog.dismiss()
        }
    }
    private fun showSuccessDialog(){
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialogue_action, viewGroup, false)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()

    }
    private fun showFailureDialog(){
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialogue_action, viewGroup, false)
        dialogView.action_image.setImageDrawable(resources.getDrawable(R.drawable.ic_warning_24))
        dialogView.action_message.text = resources.getText(R.string.submission_failes)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        builder.create().also {
            it.show()
        }
    }

    //setting navigate up button
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }




}
