package com.example.waiter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.waiter.databinding.ActivityMainLrBinding
import com.example.waiter.ui.login.FragmentLogin
import com.example.waiter.ui.regis.FragmentRegis

class LoginRegisActivity : AppCompatActivity() {
    private var binding: ActivityMainLrBinding? = null
    private var status = 0
    private lateinit var countdown_timer: CountDownTimer
    var time_in_milli_seconds = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLrBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar!!.hide()


        val login: Fragment = FragmentLogin()
        moveToFragment(login)

        binding!!.sign.setOnClickListener {
            val login: Fragment = FragmentLogin()
            moveToFragment(login)
            status = 0
            chechBtn()
        }

        binding!!.signup.setOnClickListener {
            val regis: Fragment = FragmentRegis()
            moveToFragment(regis)
            status = 1
            chechBtn()
        }

        startTimer(time_in_milli_seconds)
    }

    private fun chechBtn() {
        if (status == 0) {
            binding!!.tvSign.setTextColor(Color.parseColor("#FF000000"))
            binding!!.tvSignUp.setTextColor(Color.parseColor("#727376"))
            binding!!.orangeSignIn.visibility = View.VISIBLE
            binding!!.orangeSignUp.visibility = View.GONE
        }else{
            binding!!.tvSignUp.setTextColor(Color.parseColor("#FF000000"))
            binding!!.tvSign.setTextColor(Color.parseColor("#727376"))
            binding!!.orangeSignIn.visibility = View.GONE
            binding!!.orangeSignUp.visibility = View.VISIBLE
        }
    }

    private fun moveToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment, fragment.javaClass.simpleName).addToBackStack(null).commit()
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {

                if(isOnline(applicationContext)){
                    user()
                }else{
//                    dialoginternet()
                }
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
            }
        }
        countdown_timer.start()

    }

    @SuppressLint("NewApi")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun user() {
        val myPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val key = myPreferences.getString("code", null)
        if (key != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
            binding!!.layoutSplash.visibility = View.GONE
            binding!!.scrollView1.visibility = View.VISIBLE
        }
    }
}