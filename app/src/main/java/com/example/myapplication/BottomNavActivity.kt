package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.databinding.ActivityBottomNavBinding

@Suppress("DEPRECATION")
class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        val firstFragment = FirstFragmentBNA()
        val secondFragment = SecondFragmentBNA()
        val thirdFragment = ThirdFragmentBNA()

        setCurrentFragment(firstFragment)

        binding.buttonBNA.setOnClickListener {
            Toast.makeText(this, "xyi", Toast.LENGTH_LONG)

        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.homeBottomNav -> setCurrentFragment(firstFragment)
                R.id.messageBottomNav -> supportFragmentManager.commit {
                    replace<SecondFragmentBNA>(R.id.frameLayoutFragmentBottomNovA)
                    setReorderingAllowed(true)
                }
                R.id.profileBottomNav -> Toast.makeText(this, "setOnNavigationItemSelected" +
                        "Listener is working correct", Toast.LENGTH_LONG)
            }
            true
        }


    }



    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frameLayoutFragmentBottomNovA, fragment)
        commit()
    }
}