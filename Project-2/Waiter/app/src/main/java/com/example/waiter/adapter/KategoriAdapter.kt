package com.example.waiter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.waiter.R
import com.example.waiter.model.PayloadKategori
import com.example.waiter.ui.menu.MenuActivity


class KategoriAdapter : RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    var c: Context? = null
    var dataList: ArrayList<PayloadKategori>? = null

    constructor(context: Context, data: ArrayList<PayloadKategori>?) {
        this.c = context
        this.dataList = data
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_kategori, p0, false)
            return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItemRows(holder, position)

    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama:TextView = itemView.findViewById(R.id.tvNama)
        var tvJumlah:TextView = itemView.findViewById(R.id.tvJumlah)
        var btnhome:LinearLayout = itemView.findViewById(R.id.btnhome)
    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        holder.tvNama.text = dataList?.get(position)!!.nama
        holder.tvJumlah.text = dataList?.get(position)!!.jumlah + " item"

        holder.btnhome.setOnClickListener {
            var intent = Intent(it.context, MenuActivity::class.java)
            intent.putExtra("id_kategori", dataList?.get(position)!!.id_kategori!!)
            it.context.startActivity(intent)
        }
    }
}