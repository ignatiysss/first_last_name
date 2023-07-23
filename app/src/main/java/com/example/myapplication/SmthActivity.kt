package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SmthActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smth)
        findViewById<Button>(R.id.buttonSmthAToast).setOnClickListener {
            Toast.makeText(this, "Smth toast", Toast.LENGTH_LONG).show()
        }
    }
}

