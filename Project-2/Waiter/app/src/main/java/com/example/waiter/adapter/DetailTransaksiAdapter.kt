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
import com.example.waiter.model.PayloadDetailTransaksi
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


class DetailTransaksiAdapter : RecyclerView.Adapter<DetailTransaksiAdapter.MyViewHolder> {

    var c: Context? = null
    var dataList: ArrayList<PayloadDetailTransaksi>? = null
    private var jum: Int = 1
    private var idUser = ""

    constructor(context: Context, data: ArrayList<PayloadDetailTransaksi>?) {
        this.c = context
        this.dataList = data

        val pref: SharedPreferences = c!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_detail_transaksi, p0, false)
            return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItemRows(holder as MyViewHolder, position)

    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaProduk:TextView = itemView.findViewById(R.id.tvNamaProduk)
        var tvHarga:TextView = itemView.findViewById(R.id.tvHarga)
        var tvJumlah:TextView = itemView.findViewById(R.id.tvJumlah)
        var tvTotal:TextView = itemView.findViewById(R.id.tvTotal)
        var img:CircleImageView = itemView.findViewById(R.id.ivFoto)

    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        holder.tvNamaProduk.text = dataList?.get(position)!!.nama_menu!!
        holder.tvHarga.text = "${FormatRp.parsingRupiah(dataList?.get(position)!!.harga!!.toInt())}"
        holder.tvJumlah.text = "Kuantitas "+dataList?.get(position)!!.jumlah!!
        holder.tvTotal.text = ("Total = ${FormatRp.parsingRupiah((dataList?.get(position)!!.harga!!.toInt() * dataList?.get(position)!!.jumlah!!.toInt()))}")

        val api = InitRetrofit().getFolderImg()
        Picasso.with(c).load("$api${dataList?.get(position)!!.foto!!}").resize(200, 250).into(holder.img)
    }
}
