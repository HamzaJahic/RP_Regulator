package com.example.rpregulator.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rpregulator.adapters.CharSelectAdapter
import com.example.rpregulator.databinding.ActivityAdminBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.User
import com.firebase.ui.database.FirebaseRecyclerOptions

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        val sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val options = FirebaseRecyclerOptions.Builder<User>()
            .setQuery(UsersFirebase.databaseReference, User::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = CharSelectAdapter(options, this, CharSelectAdapter.OnClickListener {
            editor.apply {
                putString("id", it.id)
            }.apply()
            val id = it.id
            val intent = Intent(this, MainActivity::class.java)
                .putExtra("id", id)
            startActivity(intent)

        })


        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.charLista.layoutManager = layoutManager
        binding.charLista.adapter = adapter

        setContentView(binding.root)

    }


}