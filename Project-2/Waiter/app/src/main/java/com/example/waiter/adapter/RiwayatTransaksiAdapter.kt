package com.example.waiter.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.waiter.R
import com.example.waiter.model.PayloadTransaksi
import com.example.waiter.ui.detailtransaksi.DetailTransaksiActivity
import com.example.waiter.ui.menu.MenuActivity
import com.example.waiter.utils.ViewData


class RiwayatTransaksiAdapter : RecyclerView.Adapter<RiwayatTransaksiAdapter.MyViewHolder> {

    lateinit var view: ViewData
    var c: Context? = null
    var dataList: ArrayList<PayloadTransaksi>? = null
    private var idUser = ""

    constructor(context: Context, view: ViewData, data: ArrayList<PayloadTransaksi>?) {
        this.c = context
        this.dataList = data
        this.view = view

        val pref: SharedPreferences = c!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_transaksi, p0, false)
            return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItemRows(holder, position)

    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTgl:TextView = itemView.findViewById(R.id.tvTgl)
        var tvTotal:TextView = itemView.findViewById(R.id.tvTotal)
        var tvStatus:TextView = itemView.findViewById(R.id.tvStatus)
        var click:LinearLayout = itemView.findViewById(R.id.click)
    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        holder.tvTgl.text = dataList?.get(position)!!.tgl_transaksi
        holder.tvTotal.text = dataList?.get(position)!!.total_bayar

        when (dataList?.get(position)!!.status!!) {
            "0" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_red)
                holder.tvStatus.text = "Menunggu Pembayaran"
                if(dataList?.get(position)!!.foto != "") {
                    holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_orange)
                    holder.tvStatus.text = "Menunggu Konfirmasi Pembayaran"
                }
            }
            "1" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_orange)
                holder.tvStatus.text = "Pembayaran DiKonfirmasi"
            }
            "2" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_orange)
                holder.tvStatus.text = "Pembayaran DiKonfirmasi"
            }
            "3" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_blue)
                holder.tvStatus.text = "Dalam Proses Pembuatan"
            }
            "4" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_blue)
                holder.tvStatus.text = "Pemesanan Selesai Dibuat"
            }
            "5" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_green)
                holder.tvStatus.text = "Selesai"
            }
            "6" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_sts_red)
                holder.tvStatus.text = "Pemesanan Dibatalkan"
            }
        }

        holder.click.setOnClickListener {
            var intent = Intent(it.context, DetailTransaksiActivity::class.java)
            intent.putExtra("id_transaksi", dataList?.get(position)!!.id_transaksi!!)
            it.context.startActivity(intent)
        }
    }
}