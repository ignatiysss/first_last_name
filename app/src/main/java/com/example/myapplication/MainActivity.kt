package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarMain)


        val firstName = findViewById<EditText>(R.id.editFirstName)
        val lastName = findViewById<EditText>(R.id.editLastName)
        val birthDay = findViewById<EditText>(R.id.editBirthDay)
        val country = findViewById<EditText>(R.id.editCountry)
        binding.buttonApply.setOnClickListener {
            Toast.makeText(
                this,
                "${firstName.text} ${
                    lastName.text
                }, was born ${birthDay.text}, in ${country.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
        var counter = 0
        binding.buttonPlusOne.setOnClickListener {
            counter++
            findViewById<TextView>(R.id.tvCounter).text = "Counter: $counter"
        }

        binding.buttonAdd.setOnClickListener {

            val firstNumber = binding.editFirstNumber.text.toString().toIntOrNull()
            val secondNumber = binding.editSecondNumber.text.toString().toIntOrNull()
            val result: Int = if (firstNumber == null && secondNumber != null) {
                secondNumber.toInt()
            } else if (secondNumber == null && firstNumber != null) {
                firstNumber
            } else if (firstNumber != null && secondNumber != null) {
                firstNumber + secondNumber
            } else 0

            binding.tvResult.text = result.toString()
        }

        binding.smthId.setOnClickListener {
            Intent(this, SmthActivity::class.java).also {
                it.putExtra("EXTRA_FIRST_NAME", firstName.text.toString())
                it.putExtra("EXTRA_LAST_NAME", lastName.text.toString())
                it.putExtra("EXTRA_BIRTH_DAY", birthDay.text.toString())
                it.putExtra("EXTRA_COUNTRY", country.text.toString())
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAddContact -> Toast.makeText(
                this,
                "You add someone to contact",
                Toast.LENGTH_SHORT
            ).show()

            R.id.menuFavorites -> Toast.makeText(this, "You click on favorites", Toast.LENGTH_SHORT)
                .show()

            R.id.menuFeedback -> Toast.makeText(this, "You click on feedback", Toast.LENGTH_SHORT)
                .show()

            R.id.menuSettings -> Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }

            R.id.menuCloseApp -> finish()
        }

        return true
    }
}

