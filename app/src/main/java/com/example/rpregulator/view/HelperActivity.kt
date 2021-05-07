package com.example.rpregulator.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rpregulator.databinding.ActivityHelperBinding
import java.util.*
import kotlin.concurrent.schedule

class HelperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharePref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val token = sharePref.getBoolean("token", false)
        val id = sharePref.getString("id", null)
        binding = ActivityHelperBinding.inflate(layoutInflater)

        setContentView(binding.root)




        fun tokenCheck() {
            if (token == true) {
                val intent = Intent(this, MainActivity::class.java)
                        .putExtra("id", id)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }


        Timer("SettingUp", false).schedule(1500) {
            tokenCheck()
        }
    }


}