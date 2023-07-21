package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonApply)
        val editText = findViewById<EditText>(R.id.editCountry)

        button.setOnClickListener {
            val text = editText.text.toString()

            Toast.makeText(this, "Ти реально думав що мені буде не лінь писати логіку???", Toast.LENGTH_SHORT).show()
        }
    }
}