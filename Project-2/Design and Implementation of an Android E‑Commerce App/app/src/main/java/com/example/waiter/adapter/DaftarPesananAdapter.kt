package com.example.waiter.adapter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.waiter.R
import com.example.waiter.model.PayloadTemp
import com.example.waiter.model.ResponsePostTemp
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.daftarpesanan.DaftarPesananActivity
import com.example.waiter.utils.FormatRp
import com.example.waiter.utils.TmpData
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DaftarPesananAdapter : RecyclerView.Adapter<DaftarPesananAdapter.MyViewHolder> {

    var c: Context? = null
    var dataList: ArrayList<PayloadTemp>? = null
    private var jum: Int = 1
    private var idUser = ""

    constructor(context: Context, data: ArrayList<PayloadTemp>?) {
        this.c = context
        this.dataList = data

        val pref: SharedPreferences = c!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_temp, p0, false)
            return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItemRows(holder, position)

    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaProduk:TextView = itemView.findViewById(R.id.tvNamaProduk)
        var tvHarga:TextView = itemView.findViewById(R.id.tvHarga)
        var tvItem:TextView = itemView.findViewById(R.id.tvItem)
        var btnTambah:ImageView = itemView.findViewById(R.id.btnTambah)
        var btnKurang:ImageView = itemView.findViewById(R.id.btnKurang)
        var imgDelete:ImageView = itemView.findViewById(R.id.imgDelete)
        var tvTotal:TextView = itemView.findViewById(R.id.tvTotal)
        var img:CircleImageView = itemView.findViewById(R.id.ivFoto)

    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        TmpData.idProduk.add(dataList?.get(position)!!.id_menu!!)
        jum = Integer.valueOf("" + holder.tvItem.text.toString())

        holder.btnTambah.setOnClickListener {
            TmpData.idProduk.clear()
//            TmpData.kuantitas.clear()
            jum = Integer.valueOf(holder.tvItem.text.toString())

            try {
                jum += 1
                holder.tvItem.text = jum.toString()
            } catch (e: java.lang.Exception) {
            }
            updateKeranjang(dataList?.get(position)!!.id_temp!!, jum.toString())
            (c as DaftarPesananActivity?)!!.pb1.visibility = View.VISIBLE
//            (c as DaftarPesananActivity?)!!.daftarPesananPresenter.getResponse(idUser)
        }

        holder.btnKurang.setOnClickListener {
            TmpData.idProduk.clear()
//            TmpData.kuantitas.clear()
            jum = Integer.valueOf("" + holder.tvItem.text.toString())
            if (jum == 1) {
            } else if (jum > 1) {
                try {
                    jum -= 1
                    holder.tvItem.text = "" + jum
                } catch (e: Exception) {
                }
            }

            updateKeranjang(dataList?.get(position)!!.id_temp!!, jum.toString())
            (c as DaftarPesananActivity?)!!.pb1.visibility = View.VISIBLE
        }

        holder.imgDelete.setOnClickListener {
            TmpData.idProduk.clear()
            if (itemCount == 1) {
                hapusKeranjang(dataList?.get(position)!!.id_temp!!)
                (c as DaftarPesananActivity?)!!.finish()
            }else{
                hapusKeranjang(dataList?.get(position)!!.id_temp!!)
            }

        }

        holder.tvNamaProduk.text = dataList?.get(position)!!.nama_menu!!
        holder.tvHarga.text = "${FormatRp.parsingRupiah(dataList?.get(position)!!.harga!!.toInt())}"
        holder.tvItem.text = dataList?.get(position)!!.jumlah!!
        holder.tvTotal.text = ("Total = ${FormatRp.parsingRupiah((dataList?.get(position)!!.harga!!.toInt() * dataList?.get(position)!!.jumlah!!.toInt()))}")

        val api = InitRetrofit().getFolderImg()
        Picasso.with(c).load("$api${dataList?.get(position)!!.foto!!}").resize(200, 250).into(holder.img)
    }

    private fun updateKeranjang (idTemp:String, jumlah:String) {
        val api = InitRetrofit().getInitInstance()
        api.updateTemp(idTemp,jumlah).enqueue(object :
                Callback<ResponsePostTemp> {
            override fun onResponse(
                    call: Call<ResponsePostTemp>,
                    response: Response<ResponsePostTemp>
            ) {
                if (response.isSuccessful) {
                    (c as DaftarPesananActivity?)!!.daftarPesananPresenter.getResponse(idUser)
                } else {
//                    binding!!.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponsePostTemp>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }

    private fun hapusKeranjang (idTemp:String) {
        val api = InitRetrofit().getInitInstance()
        api.deleteTemp(idTemp).enqueue(object :
            Callback<ResponsePostTemp> {
            override fun onResponse(
                call: Call<ResponsePostTemp>,
                response: Response<ResponsePostTemp>
            ) {
                if (response.isSuccessful) {
                    (c as DaftarPesananActivity?)!!.daftarPesananPresenter.getResponse(idUser)
                } else {
//                    binding!!.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponsePostTemp>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}
