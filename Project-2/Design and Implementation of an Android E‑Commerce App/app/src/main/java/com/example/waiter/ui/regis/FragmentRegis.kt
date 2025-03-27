package com.example.waiter.ui.regis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waiter.R
import com.example.waiter.databinding.RegisBinding
import com.example.waiter.model.ResponseRegis
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentRegis : Fragment(R.layout.regis) {
    private var bindings: RegisBinding? = null
    private var noTelp: String = ""
    private var nama: String = ""
    private var alamat: String = ""
    private var pass: String = ""
    private var strToken: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = RegisBinding.bind(view)
        bindings = binding

        binding.btnRegis.setOnClickListener {
            noTelp = binding!!.etNoTelp.text.toString()
            nama = binding!!.etNama.text.toString()
            alamat = binding!!.etAlamat.text.toString()
            pass = binding!!.etPassword.text.toString()

            when {
                noTelp == "" -> {
                    binding!!.etNoTelp.error = "Tidak boleh kosong"
                }
                nama == "" -> {
                    binding!!.etNama.error = "Tidak boleh kosong"
                }
                alamat == "" -> {
                    binding!!.etAlamat.error = "Tidak boleh kosong"
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
        api.signUp(noTelp, nama, alamat, pass).enqueue(object : Callback<ResponseRegis> {
            override fun onResponse(call: Call<ResponseRegis>, response: Response<ResponseRegis>) {
                Log.e("responsesese", response.toString())
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Registrasi Berhasil",
                        Toast.LENGTH_SHORT
                    ).show()
                    bindings!!.loading.visibility = View.GONE
                } else {
                    proccess(2)
                    Toast.makeText(
                        requireContext(),
                        "Registrasi Gagal, Terjadi Kesalahan",
                        Toast.LENGTH_SHORT
                    ).show()
                    bindings!!.loading.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseRegis>, error: Throwable) {
                proccess(2)
                Toast.makeText(
                    requireContext(),
                    "Registrasi Gagal, Terjadi Kesalahan",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    private fun proccess (sts:Int) {
        if (sts == 1) {
            bindings!!.btnRegis.visibility = View.GONE
            bindings!!.loading.visibility = View.VISIBLE
        }else{
            bindings!!.btnRegis.visibility = View.VISIBLE
            bindings!!.loading.visibility = View.GONE
        }
    }
}