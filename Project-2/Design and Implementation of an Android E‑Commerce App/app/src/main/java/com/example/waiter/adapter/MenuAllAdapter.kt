package com.example.waiter.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.waiter.R
import com.example.waiter.model.PayloadMenu
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.menuall.MenuAllActivity
import com.squareup.picasso.Picasso


class MenuAllAdapter : RecyclerView.Adapter<MenuAllAdapter.MyViewHolder> {

    var activitys: Activity? = null
    var c: Context? = null
    var dataList: ArrayList<PayloadMenu>? = null

    constructor(context: Context, activity: Activity, data: ArrayList<PayloadMenu>?) {
        this.c = context
        this.activitys = activity
        this.dataList = data
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_menu, p0, false)
            return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        populateItemRows(holder, position)

    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvKategori:TextView = itemView.findViewById(R.id.tvKategori)
        var tvNama:TextView = itemView.findViewById(R.id.tvNama)
        var tvHarga:TextView = itemView.findViewById(R.id.tvHarga)
        var Img:ImageView = itemView.findViewById(R.id.img)
        var layoutHabis: FrameLayout = itemView.findViewById(R.id.layoutHabis)
    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        holder.tvKategori.text = dataList?.get(position)!!.nama_kategori
        holder.tvNama.text = dataList?.get(position)!!.nama
        holder.tvHarga.text = dataList?.get(position)!!.harga

        if (dataList?.get(position)!!.status == "1"){
            holder.layoutHabis.visibility = View.VISIBLE
        }else{
            holder.layoutHabis.visibility = View.GONE
        }

        val api = InitRetrofit().getFolderImg()
        Picasso.with(c).load("$api${dataList?.get(position)!!.foto!!}").into(holder.Img)

        holder.Img.setOnClickListener {
            if (dataList?.get(position)!!.status == "1") {

            }else{
                (activitys as MenuAllActivity?)!!.dialogMenu(dataList?.get(position)!!.id, dataList?.get(position)!!)
            }
        }
    }
}