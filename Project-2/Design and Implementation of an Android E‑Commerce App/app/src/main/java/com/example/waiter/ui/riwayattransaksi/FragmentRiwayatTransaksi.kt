package com.example.waiter.ui.riwayattransaksi

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waiter.R
import com.example.waiter.adapter.RiwayatTransaksiAdapter
import com.example.waiter.databinding.FragmentMejaBinding
import com.example.waiter.model.PayloadDetailTransaksi
import com.example.waiter.model.PayloadTransaksi
import com.example.waiter.utils.ViewData

class FragmentRiwayatTransaksi : Fragment(R.layout.fragment_transaksi), RiwayatTransaksiView, ViewData {
    private var bindings: FragmentMejaBinding? = null
    private lateinit var riwayatTransaksiPresenter: RiwayatTransaksiPresenter
    private lateinit var riwayatTransaksiAdapter: RiwayatTransaksiAdapter
    private var idUser = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMejaBinding.bind(view)
        bindings = binding

        val pref: SharedPreferences = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()

        binding.toolbar.tvTitle.text = "Riwayat Transaksi"

        binding!!.swlayout.setOnRefreshListener {
            binding!!.swlayout.isRefreshing = false
            binding!!.progressBar.visibility - View.VISIBLE
            riwayatTransaksiPresenter.getResponse(idUser)
        }

        riwayatTransaksiPresenter = RiwayatTransaksiPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        riwayatTransaksiPresenter.getResponse(idUser)
    }

    override fun onSuccess(payloadTransaksi: List<PayloadTransaksi>) {
        Log.d("payloadTransaksi", payloadTransaksi.toString())
//        bindings!!.recycler.visibility = View.VISIBLE
//        bindings!!.pb1.visibility = View.GONE
        riwayatTransaksiAdapter = RiwayatTransaksiAdapter(requireContext(), this, payloadTransaksi as ArrayList<PayloadTransaksi>)
        bindings?.recycler?.adapter = riwayatTransaksiAdapter
        bindings?.recycler?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        )
    }

    override fun onErrorResponse() {

    }

//    fun postmeja (idMeja:String) {
//        val api = InitRetrofit().getInitInstance()
//        api.mejaPost(idUser,idMeja).enqueue(object : Callback<Any> {
//            override fun onResponse(
//                    call: Call<Any>, response: Response<Any>) {
//                if (response.isSuccessful) {
//                    val gson = Gson()
//                    var jsonString = gson.toJson(response.body())
//                    val obj = JSONObject(jsonString)
//                    var status = obj.get("status")
//
//                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
//                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)
//
//                    if (status as Boolean) {
//                        Toast.makeText(requireContext(), "Tempat Berhasil DiPesan", Toast.LENGTH_LONG).show()
//                        mejaPresenter.getResponse()
//                    }else{
//                        Toast.makeText(requireContext(), "Tempat Gagal DiPesan", Toast.LENGTH_LONG).show()
//                    }
//                }else{
//                    Toast.makeText(requireContext(), "Terjadi kelsahan", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Any>, error: Throwable) {
//                Log.e("android1", "errornya ${error.message}")
//            }
//        })
//    }
//
//    fun postBatalMeja (idMeja:String) {
//        val api = InitRetrofit().getInitInstance()
//        api.batalMejaPost(idMeja).enqueue(object : Callback<Any> {
//            override fun onResponse(
//                    call: Call<Any>, response: Response<Any>) {
//                if (response.isSuccessful) {
//                    val gson = Gson()
//                    var jsonString = gson.toJson(response.body())
//                    val obj = JSONObject(jsonString)
//                    var status = obj.get("status")
//
//                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
//                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)
//
//                    if (status as Boolean) {
//                        Toast.makeText(requireContext(), "Pesanan Berhasil Dibatalkan", Toast.LENGTH_LONG).show()
//                        mejaPresenter.getResponse()
//                    }else{
//                        Toast.makeText(requireContext(), "Gagal", Toast.LENGTH_LONG).show()
//                    }
//                }else{
//                    Toast.makeText(requireContext(), "Terjadi kelsahan", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Any>, error: Throwable) {
//                Log.e("android1", "errornya ${error.message}")
//            }
//        })
//    }
//
    override fun sendData(data: String, status: String) {
        if (status == "pesan") {
//            dialogPost(data)
        }else{
//            dialogBatalPesan(data)
        }
    }
//
//    fun dialogPost(idMeja:String) {
//        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setContentView(R.layout.dialog_post_meja)
//
//        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
//        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
//        dialog.show()
//
//        btnSetuju.setOnClickListener {
//            postmeja(idMeja)
//            dialog.dismiss()
//        }
//
//        btnBatal.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
//
//    fun dialogBatalPesan(idMeja:String) {
//        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setContentView(R.layout.dialog_batal_meja)
//
//        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
//        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
//        dialog.show()
//
//        btnSetuju.setOnClickListener {
//            postBatalMeja(idMeja)
//            dialog.dismiss()
//        }
//
//        btnBatal.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
}