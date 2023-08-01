package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.databinding.ActivitySmthBinding

@Suppress("DEPRECATION")
class SmthActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySmthBinding


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSmth)

        val listForSpinnerToast = listOf("January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December")

        val adapterForSpinnerToast = ArrayAdapter<String>(this, androidx.constraintlayout
            .widget.R.layout.support_simple_spinner_dropdown_item, listForSpinnerToast)
        binding.spinnerToast.adapter = adapterForSpinnerToast

        binding.spinnerToast.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, id: Long) {
                Toast.makeText(this@SmthActivity, "You selected ${adapterView
                    ?.getItemAtPosition(i).toString()}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSmthAToast.setOnClickListener {
            Toast.makeText(
                this, "${
                    intent.getStringExtra("EXTRA_FIRST_NAME")
                } ${
                    intent.getStringExtra("EXTRA_LAST_NAME")
                } was born ${
                    intent.getStringExtra("EXTRA_BIRTH_DAY")
                } in ${intent.getStringExtra("EXTRA_COUNTRY")}", Toast.LENGTH_LONG
            ).show()

        }

        binding.buttonSmthAPermission.setOnClickListener {
            requestPermission()
        }

        val optionsForSingleChoiceDialog = arrayOf(
            "Leave default",
            "Choose between most useful",
            "Choose by your self"
        )

        val leaveDefault = AlertDialog.Builder(this)
            .setTitle(optionsForSingleChoiceDialog[0])
            .setMessage("Are you sure that you need leave default photo")
            .setPositiveButton("Yes") { _, _ ->
                binding.smthAPhoto.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.st_flowers,
                        theme
                    )
                )

            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Ok :)", Toast.LENGTH_LONG).show()
            }.create()

        val chooseFromMostUseful = Toast.makeText(this, "I am too lazy for this", Toast.LENGTH_LONG)

        val chooseByYourSelf = AlertDialog.Builder(this)
            .setTitle(optionsForSingleChoiceDialog[2])
            .setMessage("Are you sure?")
            .setIcon(R.drawable.baseline_add_photo_alternate_24)
            .setPositiveButton("Yes") { _, _ ->
                Intent(Intent.ACTION_GET_CONTENT).also {
                    it.type = "image/*"
                    startActivityForResult(it, 0)
                }
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Ok :)", Toast.LENGTH_LONG).show()
            }.create()


        var stateSingleChooseForeSmthAPhoto = 0
        val singleChoiceForSmthAPhoto = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Choose action")
            .setSingleChoiceItems(optionsForSingleChoiceDialog, 0) { _, i ->
                stateSingleChooseForeSmthAPhoto = i
            }
            .setPositiveButton("Yes") { _, _ ->
                when (stateSingleChooseForeSmthAPhoto) {
                    0 -> leaveDefault.show()
                    1 -> chooseFromMostUseful.show()
                    2 -> chooseByYourSelf.show()
                }
            }
            .create()


        binding.buttonSmthAChangePhoto.setOnClickListener {
            singleChoiceForSmthAPhoto.show()
        }

        binding.buttonSmthANextA.setOnClickListener {
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

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun hasCameraPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

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
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAddContact -> Toast.makeText(
                this,
                "You add someone to contact ешкере",
                Toast.LENGTH_SHORT
            ).show()

            R.id.menuFavorites -> Toast.makeText(this, "You click on favorites", Toast.LENGTH_SHORT)
                .show()

            R.id.menuFeedback -> Toast.makeText(this, "You click on feedback", Toast.LENGTH_SHORT)
                .show()

            R.id.menuSettings -> Toast.makeText(this, "You click on settings", Toast.LENGTH_SHORT)
                .show()

            R.id.menuCloseApp -> finish()
        }

        return true
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
                    Log.d("smthLog", "${permissions[i]} granted.")
                }
            }
        }
    }
}