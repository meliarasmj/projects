package com.example.waiter.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.waiter.R
import com.example.waiter.model.PayloadKategori
import com.example.waiter.model.PayloadMeja
import com.example.waiter.ui.menu.MenuActivity
import com.example.waiter.utils.ViewData


class MejaAdapter : RecyclerView.Adapter<MejaAdapter.MyViewHolder> {

    lateinit var view: ViewData
    var c: Context? = null
    var dataList: ArrayList<PayloadMeja>? = null
    private var idUser = ""

    constructor(context: Context, view: ViewData, data: ArrayList<PayloadMeja>?) {
        this.c = context
        this.dataList = data
        this.view = view

        val pref: SharedPreferences = c!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()
    }

    @NonNull
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(c).inflate(R.layout.item_meja, p0, false)
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
        var ImgShadeow:ImageView = itemView.findViewById(R.id.shadow)
        var ImgShadeows:ImageView = itemView.findViewById(R.id.shadows)
        var btnpesan:LinearLayout = itemView.findViewById(R.id.linearLayout10)
    }

    private fun populateItemRows(holder: MyViewHolder, position: Int) {
        holder.tvNama.text = dataList?.get(position)!!.nama

        holder.btnpesan.setOnClickListener {
            if (dataList?.get(position)!!.status == "1"){
                if (dataList?.get(position)!!.id_user == idUser) {
                    view.sendData(dataList?.get(position)!!.id_meja!!, "batal")
                }
            }else{
                view.sendData(dataList?.get(position)!!.id_meja!!, "pesan")
            }
        }

        if (dataList?.get(position)!!.status == "1") {
            if (dataList?.get(position)!!.id_user == idUser) {
                holder.ImgShadeows.visibility = View.VISIBLE
                holder.ImgShadeow.visibility = View.GONE
            }else{
                holder.ImgShadeows.visibility = View.GONE
                holder.ImgShadeow.visibility = View.VISIBLE
            }

        }else{
            holder.ImgShadeow.visibility = View.GONE
        }

//        holder.btnhome.setOnClickListener {
//            var intent = Intent(it.context, MenuActivity::class.java)
//            intent.putExtra("id_kategori", dataList?.get(position)!!.id_kategori!!)
//            it.context.startActivity(intent)
//        }
    }
}