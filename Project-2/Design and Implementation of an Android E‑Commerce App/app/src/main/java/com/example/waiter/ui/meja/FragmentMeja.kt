package com.example.waiter.ui.meja

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waiter.MainActivity
import com.example.waiter.R
import com.example.waiter.adapter.KategoriAdapter
import com.example.waiter.adapter.MejaAdapter
import com.example.waiter.databinding.FragmentHomeBinding
import com.example.waiter.databinding.FragmentMejaBinding
import com.example.waiter.model.PayloadKategori
import com.example.waiter.model.PayloadMeja
import com.example.waiter.model.ResponsePostTemp
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.daftarpesanan.DaftarPesananActivity
import com.example.waiter.ui.menuall.MenuAllActivity
import com.example.waiter.utils.TmpData
import com.example.waiter.utils.ViewData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMeja : Fragment(R.layout.fragment_meja), MejaView, ViewData {
    private var bindings: FragmentMejaBinding? = null
    private lateinit var mejaPresenter: MejaPresenter
    private lateinit var mejaAdapter: MejaAdapter
    private var idUser = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMejaBinding.bind(view)
        bindings = binding

        val pref: SharedPreferences = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()

        binding.toolbar.tvTitle.text = "Pilih Tempat"

        binding!!.swlayout.setOnRefreshListener {
            binding!!.swlayout.isRefreshing = false
            binding!!.progressBar.visibility - View.VISIBLE
            mejaPresenter.getResponse()
        }

        mejaPresenter = MejaPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        mejaPresenter.getResponse()
    }

    override fun onSuccess(payloadMeja: List<PayloadMeja>) {
        Log.d("payloadKategori", payloadMeja.toString())
//        bindings!!.recycler.visibility = View.VISIBLE
//        bindings!!.pb1.visibility = View.GONE
        mejaAdapter = MejaAdapter(requireContext(), this, payloadMeja as ArrayList<PayloadMeja>)
        bindings?.recycler?.adapter = mejaAdapter
        bindings?.recycler?.layoutManager = GridLayoutManager(requireContext()  , 2)
    }

    override fun onErrorResponse() {

    }

    fun postmeja (idMeja:String) {
        val api = InitRetrofit().getInitInstance()
        api.mejaPost(idUser,idMeja).enqueue(object : Callback<Any> {
            override fun onResponse(
                    call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    var jsonString = gson.toJson(response.body())
                    val obj = JSONObject(jsonString)
                    var status = obj.get("status")

                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)

                    if (status as Boolean) {
                        Toast.makeText(requireContext(), "Tempat Berhasil DiPesan", Toast.LENGTH_LONG).show()
                        mejaPresenter.getResponse()
                    }else{
                        Toast.makeText(requireContext(), "Tempat Gagal DiPesan", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Terjadi kelsahan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    fun postBatalMeja (idMeja:String) {
        val api = InitRetrofit().getInitInstance()
        api.batalMejaPost(idMeja).enqueue(object : Callback<Any> {
            override fun onResponse(
                    call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    var jsonString = gson.toJson(response.body())
                    val obj = JSONObject(jsonString)
                    var status = obj.get("status")

                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)

                    if (status as Boolean) {
                        Toast.makeText(requireContext(), "Pesanan Berhasil Dibatalkan", Toast.LENGTH_LONG).show()
                        mejaPresenter.getResponse()
                    }else{
                        Toast.makeText(requireContext(), "Gagal", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Terjadi kelsahan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    override fun sendData(data: String, status: String) {
        if (status == "pesan") {
            dialogPost(data)
        }else{
            dialogBatalPesan(data)
        }
    }

    fun dialogPost(idMeja:String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_post_meja)

        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
        dialog.show()

        btnSetuju.setOnClickListener {
            postmeja(idMeja)
            dialog.dismiss()
        }

        btnBatal.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun dialogBatalPesan(idMeja:String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_batal_meja)

        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
        dialog.show()

        btnSetuju.setOnClickListener {
            postBatalMeja(idMeja)
            dialog.dismiss()
        }

        btnBatal.setOnClickListener {
            dialog.dismiss()
        }
    }
}