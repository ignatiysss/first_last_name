package com.example.myapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val firstName = findViewById<EditText>(R.id.editFirstName)
        val lastName = findViewById<EditText>(R.id.editLastName)
        val birthDay = findViewById<EditText>(R.id.editBirthDay)
        val country = findViewById<EditText>(R.id.editCountry)
        binding.buttonApply.setOnClickListener {
            Toast.makeText(this, "Значить так ти ${firstName.text.toString()} ${lastName.text.toString()}, народився ${birthDay.text.toString()}, в ${country.text.toString()}", Toast.LENGTH_SHORT).show()
        }
        var counter = 0
        binding.buttonPlusOne.setOnClickListener {
            counter++
            findViewById<TextView>(R.id.tvCounter).text = "Counter: $counter"
        }

        binding.buttonAdd.setOnClickListener {
            findViewById<TextView>(R.id.tvResult).text = (findViewById<EditText>(R.id.editFirstNumber).text.toString().toInt() +
                    findViewById<EditText>(R.id.editSecondNumber).text.toString().toInt()).toString()
        }
        binding.smthId.setOnClickListener {
            Toast.makeText(this, "Значить так ти ${firstName.text.toString()} ${lastName.text.toString()}, народився ${birthDay.text.toString()}, в ${country.text.toString()}", Toast.LENGTH_SHORT).show()
        }
    }
}