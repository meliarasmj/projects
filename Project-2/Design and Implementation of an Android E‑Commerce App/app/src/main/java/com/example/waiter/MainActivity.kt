package com.example.waiter

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waiter.ui.home.FragmentHome
import com.example.waiter.ui.meja.FragmentMeja
import com.example.waiter.ui.riwayattransaksi.FragmentRiwayatTransaksi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    private var lastPressedTime: Long = 0
    private val PERIOD = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val frag = FragmentHome()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragLayouts, frag)
        transaction.commit()

        navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFrag("home")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    showFrag("meja")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    showFrag("histori")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_log_out -> {
                    showFrag("logout")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

    }

    private fun showFrag(frags: String) {
        when (frags) {
            "home" -> {
                val frag = FragmentHome()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.fragLayouts, frag)
                transaction.commit()
            }
            "meja" -> {
                val frag = FragmentMeja()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.fragLayouts, frag)
                transaction.commit()
            }
            "histori" -> {
                val frag = FragmentRiwayatTransaksi()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.fragLayouts, frag)
                transaction.commit()
            }
            "logout" -> {
                dialogLogOut()
            }
            else -> {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            when (event.action) {
                KeyEvent.ACTION_DOWN -> {
                    if (event.downTime - lastPressedTime < PERIOD) {
                        val baIntent = Intent(Intent.ACTION_MAIN)
                        baIntent.addCategory(Intent.CATEGORY_HOME)
                        baIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(baIntent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Tekan sekali lagi untuk keluar.",
                            Toast.LENGTH_SHORT
                        ).show()
                        lastPressedTime = event.eventTime
                    }
                    return true
                }
            }
        }
        return false
    }

    fun dialogLogOut() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)

        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
        dialog.show()

        btnSetuju.setOnClickListener {
            val pref: SharedPreferences = getSharedPreferences(
                    "login",
                    Context.MODE_PRIVATE
            )
            pref.edit().clear().commit()

            val intent = Intent(this@MainActivity, CloseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            dialog.dismiss()
        }

        btnBatal.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun requestPermission() {

        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {

                    } else {
                        if (report.isAnyPermissionPermanentlyDenied) {
//                            Toast.makeText(this@MainActivity, "Untuk mengakses Menu-menu Iznkan Aplikasi dipengaturan", Toast.LENGTH_LONG).show()
                        } else {
//                            Toast.makeText(this@MainActivity, "Untuk memaksimalkan Aplikasi, Harap izinkan beberapa izin yang tampil", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }).check()
    }
}