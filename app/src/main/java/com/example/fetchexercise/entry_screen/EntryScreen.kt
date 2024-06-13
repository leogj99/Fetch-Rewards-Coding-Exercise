package com.example.fetchexercise.entry_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchexercise.R
import com.example.fetchexercise.data_display.DataDisplay

class EntryScreen : AppCompatActivity() {

    private lateinit var titleMenu: TextView
    private lateinit var buttonMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleMenu = findViewById(R.id.title_menu)
        buttonMenu = findViewById(R.id.menu_button)
        buttonMenu.setOnClickListener {
            intent = Intent(this, DataDisplay::class.java)
            startActivity(intent)
        }

    }
}