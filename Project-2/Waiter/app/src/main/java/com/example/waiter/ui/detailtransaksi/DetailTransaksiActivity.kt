package com.example.waiter.ui.detailtransaksi

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
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waiter.R
import com.example.waiter.adapter.DetailTransaksiAdapter
import com.example.waiter.databinding.ActivityDetailTransaksiBinding
import com.example.waiter.model.PayloadDetailTransaksi
import com.example.waiter.model.ResponsePostTemp
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.pembayaran.PembayaranActivity
import com.example.waiter.utils.FormatRp
import com.example.waiter.utils.TmpData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTransaksiActivity : AppCompatActivity(), DetailTransaksiView {
    private lateinit var binding: ActivityDetailTransaksiBinding
    lateinit var detailTransaksiPresenter: DetailTransaksiPresenter
    lateinit var pb1: ProgressBar
    private lateinit var detailTransaksiAdapter: DetailTransaksiAdapter
    private var idUser = ""
    private var idTransaksi = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val pref: SharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()

        binding.toolbar.tvTitle.text = "Detail Transaksi"

        binding!!.recycler.isFocusable = false
        pb1 = findViewById(R.id.progressBar)

        idTransaksi = intent.getStringExtra("id_transaksi").toString()

        binding!!.swlayout.setOnRefreshListener {
            binding!!.swlayout.isRefreshing = false
            TmpData.idProduk.clear()
            binding!!.progressBar.visibility - View.VISIBLE
            detailTransaksiPresenter.getResponse(idTransaksi)
        }

        detailTransaksiPresenter = DetailTransaksiPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        detailTransaksiPresenter.getResponse(idTransaksi)
    }

    override fun onSuccess(payloadDetailTransaksi: List<PayloadDetailTransaksi>, total: String, statusTransaksi: String, pembayaran: String, foto: String, ongkir: String, subtotal: String) {
        Log.d("payloadDetailTransaksi", payloadDetailTransaksi.toString())
        if (statusTransaksi == "0") {
            binding!!.btnBatal.visibility = View.VISIBLE
            if(pembayaran.toInt() == 1){
                if (foto == "") {
                    binding!!.btnBayar.visibility = View.VISIBLE
                }
            }

            binding!!.btnBatal.setOnClickListener {
                dialogBatalkanPesanan(idTransaksi)
            }
            binding!!.btnBayar.setOnClickListener {
                var intent = Intent(it.context, PembayaranActivity::class.java)
                intent.putExtra("id_transaksi", idTransaksi)
                it.context.startActivity(intent)
            }
        }else{
            binding!!.btnBatal.visibility = View.GONE
        }
        binding.tvOngkir.text = FormatRp.parsingRupiah(ongkir.toInt())
        binding.tvSubTotal.text = FormatRp.parsingRupiah(subtotal.toInt())
        binding.tvTotal.text = FormatRp.parsingRupiah(total.toInt())
        pb1.visibility = View.GONE
        detailTransaksiAdapter = DetailTransaksiAdapter(this, payloadDetailTransaksi as ArrayList<PayloadDetailTransaksi>)
        binding.recycler?.adapter = detailTransaksiAdapter
        binding.recycler?.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
    }

    override fun onErrorResponse() {

    }

    private fun batalTransaksi (idTransaksi: String) {
        val api = InitRetrofit().getInitInstance()
        api.batalTransaksi(idTransaksi).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    var jsonString = gson.toJson(response.body())
                    val obj = JSONObject(jsonString)
                    var status = obj.get("status")

                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)

                    if (status as Boolean) {
                        Toast.makeText(this@DetailTransaksiActivity, "Transaksi Berhasil Dibatalkan", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this@DetailTransaksiActivity, "Terjadi kelsahan", Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this@DetailTransaksiActivity, "Terjadi kelsahan", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    fun dialogBatalkanPesanan(idTransaksi:String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_batal_pesanan)

        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
        dialog.show()

        btnSetuju.setOnClickListener {
            batalTransaksi(idTransaksi)
            dialog.dismiss()
        }

        btnBatal.setOnClickListener {
            dialog.dismiss()
        }
    }
}