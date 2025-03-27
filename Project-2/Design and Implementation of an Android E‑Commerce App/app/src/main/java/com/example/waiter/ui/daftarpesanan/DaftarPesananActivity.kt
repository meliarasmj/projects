package com.example.waiter.ui.daftarpesanan

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waiter.R
import com.example.waiter.adapter.DaftarPesananAdapter
import com.example.waiter.databinding.DaftarPesananBinding
import com.example.waiter.model.PayloadTemp
import com.example.waiter.model.ResponsePostTemp
import com.example.waiter.model.ResponsePostTransaksi
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.pilihlokasi.PilihLokasiActivity
import com.example.waiter.utils.FormatRp
import com.example.waiter.utils.TmpData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarPesananActivity : AppCompatActivity(), DaftarPesananView {
    private lateinit var binding: DaftarPesananBinding
    lateinit var daftarPesananPresenter: DaftarPesananPresenter
    lateinit var pb1: ProgressBar
    private lateinit var daftarPesananAdapter: DaftarPesananAdapter
    private var idUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DaftarPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val pref: SharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()

        binding.toolbar.tvTitle.text = "Daftar Pesanan"
        binding.btnPembayaran.setOnClickListener {
            Log.d("tmpdata", TmpData.idProduk.toString())
//            binding.btnPembayaran.visibility = View.GONE
//            dialogPost("")
            dialog(this, "")
        }

        binding!!.recycler.isFocusable = false
        pb1 = findViewById(R.id.progressBar)

        binding!!.swlayout.setOnRefreshListener {
            binding!!.swlayout.isRefreshing = false
            TmpData.idProduk.clear()
            binding!!.progressBar.visibility - View.VISIBLE
            daftarPesananPresenter.getResponse(idUser)
        }

        daftarPesananPresenter = DaftarPesananPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        daftarPesananPresenter.getResponse(idUser)
    }

    override fun onSuccess(payloadTemp: List<PayloadTemp>, total: String) {
        Log.d("payloadKategori", payloadTemp.toString())
        binding.lcart.visibility = View.VISIBLE
//        if (total == "0") {
//            binding.lcart.visibility = View.GONE
//        }else{
//            binding.lcart.visibility = View.VISIBLE
//        }
//        bindings!!.recycler.visibility = View.VISIBLE
        binding.tvTotal.text = FormatRp.parsingRupiah(total.toInt())
        pb1.visibility = View.GONE
        daftarPesananAdapter = DaftarPesananAdapter(this, payloadTemp as ArrayList<PayloadTemp>)
        binding.recycler?.adapter = daftarPesananAdapter
        binding.recycler?.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
    }

    override fun onErrorResponse() {

    }

    private fun postTransaksi (idMeja:String) {
        val api = InitRetrofit().getInitInstance()
        api.postTransaksi(idUser,idMeja, "", "", "").enqueue(object :Callback<Any> {
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
                        Toast.makeText(this@DaftarPesananActivity, "Transaksi Berhasil", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        if (listData.payload == "meja") {
                            Toast.makeText(this@DaftarPesananActivity, "Pesan Tempat Terlebih Dahulu", Toast.LENGTH_LONG).show()
                        }else{
                            finish()
                        }
                    }

                }else{
                    Toast.makeText(this@DaftarPesananActivity, "Terjadi kelsahan", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    fun dialogPost(idMeja:String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_post_transaksi)

        val btnBatal = dialog.findViewById<Button>(R.id.btnBatal)
        val btnSetuju = dialog.findViewById<Button>(R.id.btnSetuju)
        dialog.show()

        btnSetuju.setOnClickListener {
            postTransaksi(idMeja)
            dialog.dismiss()
        }

        btnBatal.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun dialog(context: Context, idMeja:String) {
        val options = arrayOf<CharSequence>("Makan Di Tempat", "Buat Pesanan", "Batal")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            when {
                options[item] == "Makan Di Tempat" -> {
                    postTransaksi(idMeja)
                }
                options[item] == "Buat Pesanan" -> {
                    val myIntent = Intent(this, PilihLokasiActivity::class.java)
//                    myIntent.putExtra("pembayaran", "2")
                    startActivity(myIntent)
                    finish()
//                    postTransaksi("0")
                }
                options[item] == "Batal" -> {
                    dialog.dismiss()
                }
            }
        })
        builder.show()
    }
}