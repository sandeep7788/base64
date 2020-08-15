package com.example.base64

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.base64.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import android.telecom.Call as Call1


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var CAMERA_PERMISSION_CODE= 100
    var STORAGE_PERMISSION_CODE=101
    var Residential_Commerical="0"
    val result = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        checkPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE);

        binding.selectPropertyRg.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change :"+
                        " ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                Residential_Commerical=radio.text.toString()
            })
    }

    // Function to check and request permission
    fun checkPermission(permission: String, requestCode: Int) {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat
                .requestPermissions(
                    this@MainActivity, arrayOf(permission),
                    requestCode
                )
        } else {
            Toast
                .makeText(
                    this@MainActivity,
                    "Permission already granted",
                    Toast.LENGTH_SHORT
                )
                .show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super
            .onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {

                // Showing the toast message
                Toast.makeText(
                    this@MainActivity,
                    "Camera Permission Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Camera Permission Denied",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "Storage Permission Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Storage Permission Denied",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    fun login1()
    {
        var preferences= Services()
        val loginResponseCall: Call<Datum>? =
            preferences.get_dashbord_menu()!!.getLandingPageReport(binding.name.toString(),binding.LandlordMobileno.toString(),
            binding.Address.text.toString(),binding.LocationArea.text.toString(),binding.City.text.toString(),
            binding.flooeNumber.text.toString(),binding.PinCode.text.toString(),Residential_Commerical,result.toString()
            )
        //og.e("@@l",e1.text.toString()+""+e2.text.toString())
        loginResponseCall!!.enqueue(object : retrofit2.Callback<Datum>
        {
            override fun onFailure(call: Call<Datum>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message.toString()+" ",Toast.LENGTH_LONG);
            }

            override fun onResponse(call: Call<Datum>, response: Response<Datum>) {
                Log.e("@@",response.body()!!.status.toString()+" ")
                Toast.makeText(this@MainActivity,"Successfully submited",Toast.LENGTH_LONG);
            }

        })
    }
    fun getpropertyType()
    {
        var totalAmount: Int = 0
        val result = StringBuilder()
        result.append("Selected Items")
        if (binding.FlatApartment.isChecked
        ) {
            result.append("\nPizza 100Rs")
            totalAmount += 100
        }
        if (binding.oneBhk.isChecked) {
            result.append("\nCoffee 50Rs")
            totalAmount += 50
        }
        if (binding.twoBhk.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }
        if (binding.threeBhk.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }

        if (binding.fourBhk.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }

        if (binding.officeSpace.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }

        if (binding.house.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }

        if (binding.villa.isChecked) {
            result.append("\nBurger 120Rs")
            totalAmount += 120
        }

        result.append("" + totalAmount + ",")
        Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()

    }
}