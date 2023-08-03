package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityBottomNavBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        val firstFragment = FirstFragmentTBA()
        val secondFragment = SecondFragmentTBA()
        val thirdFragment = ThirdFragmentTBA()

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.homeBottomNav -> setCurrentFragment(firstFragment)
                R.id.messageBottomNav -> setCurrentFragment(secondFragment)
                R.id.profileBottomNav -> setCurrentFragment(thirdFragment)
            }
            true
        }


    }



    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frameLayoutFragmentBottomNovA, fragment)
        commit()
    }
}