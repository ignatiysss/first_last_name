package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonApply)
        val firstName = findViewById<EditText>(R.id.editFirstName)
        val lastName = findViewById<EditText>(R.id.editLastName)
        val birthDay = findViewById<EditText>(R.id.editBirthDay)
        val country = findViewById<EditText>(R.id.editCountry)
        button.setOnClickListener {
            Toast.makeText(this, "Значить так ти ${firstName.text.toString()} ${lastName.text.toString()}, народився ${birthDay.text.toString()}, в ${country.text.toString()}", Toast.LENGTH_SHORT).show()
        }
        val buttonPlusOne = findViewById<Button>(R.id.buttonPlusOne)
        var counter = 0
        buttonPlusOne.setOnClickListener {
            counter++
            findViewById<TextView>(R.id.tvCounter).text = "Counter: $counter"
        }
    }
}