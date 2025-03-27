package com.example.waiter.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.waiter.MainActivity
import com.example.waiter.R
import com.example.waiter.databinding.LoginBinding
import com.example.waiter.model.ResponseLogin
import com.example.waiter.network.InitRetrofit
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentLogin : Fragment(R.layout.login) {
    private var bindings: LoginBinding? = null
    private var noTelp: String = ""
    private var pass: String = ""
    private var strToken: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = LoginBinding.bind(view)
        bindings = binding

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAGstoken", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            strToken = token.toString()
//            Toast.makeText(this, "bb$strToken", Toast.LENGTH_LONG).show()
            Log.d("TAGstoken", token.toString())
        })

        binding.btnLogin.setOnClickListener {
            noTelp = binding!!.etNoTelp.text.toString()
            pass = binding!!.etPassword.text.toString()

            when {
                noTelp == "" -> {
                    binding!!.etNoTelp.error = "Tidak boleh kosong"
                }
                pass == "" -> {
                    binding!!.etPassword.error = "Tidak boleh kosong"
                }
                else -> {
                    proccess(1)
                    binding!!.loading.visibility = View.VISIBLE
                    loadData()
                }
            }
        }

    }

    fun loadData(){
        val api = InitRetrofit().getInitInstance()
        api.login(noTelp, pass, strToken).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    val jsonresponse = response.body()?.payload
                    if (noTelp == jsonresponse?.no_hp) {

                        requireContext().getSharedPreferences("login", AppCompatActivity.MODE_PRIVATE)
                                .edit()
                                .putString("code", jsonresponse.id)
                                .putString("username", jsonresponse.no_hp)
                                .putString("nama", jsonresponse.nama)
                                .apply()

                        val myIntent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(myIntent)
                        bindings!!.loading.visibility = View.GONE
                    } else {
                        proccess(2)
                        Toast.makeText(
                                requireContext(),
                                "Login Gagal, Periksa kembali Nomor Telephone dan Password",
                                Toast.LENGTH_SHORT
                        ).show()
                        bindings!!.loading.visibility = View.GONE
                    }
                } else {
                    proccess(2)
                    Toast.makeText(
                            requireContext(),
                            "Login Gagal, Periksa kembali Nomor Telephone dan Password",
                            Toast.LENGTH_SHORT
                    ).show()
                    bindings!!.loading.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, error: Throwable) {
                proccess(2)
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    private fun proccess (sts:Int) {
        if (sts == 1) {
            bindings!!.btnLogin.visibility = View.GONE
            bindings!!.loading.visibility = View.VISIBLE
        }else{
            bindings!!.btnLogin.visibility = View.VISIBLE
            bindings!!.loading.visibility = View.GONE
        }
    }
}