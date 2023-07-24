package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

@Suppress("DEPRECATION")
class SmthActivity: AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smth)
        findViewById<Button>(R.id.buttonSmthAToast).setOnClickListener {
            Toast.makeText(this, "${
                intent.getStringExtra("EXTRA_FIRST_NAME")} ${intent.getStringExtra(
                "EXTRA_LAST_NAME")
            } was born ${
                intent.getStringExtra("EXTRA_BIRTH_DAY") 
            } in ${intent.getStringExtra("EXTRA_COUNTRY")}", Toast.LENGTH_LONG).show()

        }
        findViewById<Button>(R.id.buttonSmthAPermission).setOnClickListener {
            requestPermission()
        }

        findViewById<Button>(R.id.buttonSmthAChangePhoto).setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }

        findViewById<Button>(R.id.buttonSmthANextA).setOnClickListener {
            Intent(this, ToolBarActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            val uri = data?.data
            findViewById<ImageView>(R.id.smthAPhoto).setImageURI(uri)
        }
    }

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(this,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasCameraPermission() = ActivityCompat.checkSelfPermission(this,
    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun requestPermission() {
        val permissionToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (!hasLocationForegroundPermission()) permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (!hasLocationBackgroundPermission()) permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        if (!hasCameraPermission()) permissionToRequest.add(Manifest.permission.CAMERA)
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menuAddContact -> Toast.makeText(this, "You add someone to contact", Toast.LENGTH_SHORT).show()
//            R.id.menuFavorites -> Toast.makeText(this, "You click on favorites", Toast.LENGTH_SHORT).show()
//            R.id.menuFeedback -> Toast.makeText(this, "You click on feedback", Toast.LENGTH_SHORT).show()
//            R.id.menuSettings -> Toast.makeText(this, "You click on settings", Toast.LENGTH_SHORT).show()
//            R.id.menuCloseApp -> finish()
//        }
//
//        return true
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("smthLog",  "${permissions[i]} granted.")
                }
            }
        }
    }
}



