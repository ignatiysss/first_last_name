package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class SmthActivity: AppCompatActivity() {



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
    }

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(this,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        var permissionToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (!hasLocationForegroundPermission()) permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (!hasLocationBackgroundPermission()) permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }

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



