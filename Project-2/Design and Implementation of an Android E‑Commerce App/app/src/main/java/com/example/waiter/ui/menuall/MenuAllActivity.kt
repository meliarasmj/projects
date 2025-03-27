package com.example.waiter.ui.menuall

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.waiter.R
import com.example.waiter.adapter.MenuAllAdapter
import com.example.waiter.databinding.ActivityMenuBinding
import com.example.waiter.model.PayloadMenu
import com.example.waiter.network.InitRetrofit
import com.example.waiter.ui.daftarpesanan.DaftarPesananActivity
import com.example.waiter.ui.tambahtemp.TambahTempPresenter
import com.example.waiter.ui.tambahtemp.TambahTempView
import com.squareup.picasso.Picasso
import java.util.*

class MenuAllActivity : AppCompatActivity(), MenuAllView, TambahTempView {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var menuAllPresenter: MenuAllPresenter
    private lateinit var tambahTempPresenter: TambahTempPresenter
    private lateinit var menuAllAdapter: MenuAllAdapter
    private var idUser = ""
    private var jum: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val pref: SharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()

        binding.swlayout.setOnRefreshListener {
            binding.swlayout.isRefreshing = false
            binding.pb1.visibility = View.VISIBLE
            menuAllPresenter.getResponse("")
        }

        menuAllPresenter = MenuAllPresenter(this)
        tambahTempPresenter = TambahTempPresenter(this)

        menuAllPresenter.getResponse("")

        binding.search.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(
                            charSequence: CharSequence,
                            i: Int,
                            i1: Int,
                            i2: Int,
                    ) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        if (charSequence.isEmpty()) {
                            menuAllPresenter.getResponse("")
                        }
                        if (charSequence.length > 2) {
                            val nmMenu = charSequence.toString()
                            menuAllPresenter.getResponse(nmMenu)
                        }
                    }

                    override fun afterTextChanged(editable: Editable) {}
                },
        )

        binding.fabDaftar.setOnClickListener {
            startActivity(Intent(this, DaftarPesananActivity::class.java))
        }

//        iniToolbarLayout()
    }

//    private fun iniToolbarLayout() {
//        binding.appBar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
//            var isShow = true
//            var scrollRange = -1
//            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.totalScrollRange
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    binding.tvNama.setTextColor(resources.getColor(R.color.white))
//                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//                    isShow = true
//                } else if (isShow) {
//                    binding.tvNama.setTextColor(resources.getColor(R.color.white))
//                    supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//                    isShow = false
//                }
//            }
//        })
//    }

    override fun onSuccess(payloadMenu: List<PayloadMenu>) {
        Log.d("payloadMenu", payloadMenu.toString())
//        bindings!!.recycler.visibility = View.VISIBLE
        binding!!.pb1.visibility = View.GONE
        menuAllAdapter = MenuAllAdapter(this, this, payloadMenu as ArrayList<PayloadMenu>)
        binding.recycler?.adapter = menuAllAdapter
        binding.recycler?.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onSuccessPostTemp(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onErrorResponse() {
        binding.pb1.visibility = View.GONE
    }

    fun dialogMenu(idMenu: String, get: PayloadMenu) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_menu)

        val tvMenu = dialog.findViewById<TextView>(R.id.tvNama)
        val tvHarga = dialog.findViewById<TextView>(R.id.tvHarga)
        val tvKet = dialog.findViewById<TextView>(R.id.tvKet)

        val btnAddMenu = dialog.findViewById<Button>(R.id.btnAdd)
        val imgKurang = dialog.findViewById<ImageView>(R.id.imgKurang)
        val imgTambah = dialog.findViewById<ImageView>(R.id.imgTambah)
        val jumlah = dialog.findViewById<TextView>(R.id.jumlah)
        val etket = dialog.findViewById<EditText>(R.id.etket)
        val img = dialog.findViewById<ImageView>(R.id.img)
        dialog.show()

        tvMenu.text = get.nama
        tvHarga.text = get.harga
        tvKet.text = Html.fromHtml(get.deskripsi)

        val api = InitRetrofit().getFolderImg()
        Picasso.with(this).load("$api${get.foto}").into(img)

        imgKurang.setOnClickListener {
            jum = Integer.valueOf("" + jumlah.text.toString())
            if (jum == 1) {
            } else if (jum > 1) {
                try {
                    jum -= 1
                    jumlah.text = "" + jum
                } catch (e: Exception) {
                }
            }
        }

        imgTambah.setOnClickListener {
            jum = Integer.valueOf(jumlah.text.toString())

            try {
                jum += 1
                jumlah.text = jum.toString()
            } catch (e: java.lang.Exception) {
            }
        }

        btnAddMenu.setOnClickListener {
            tambahTempPresenter.postTemp(idUser,idMenu,jum.toString(),etket.text.toString())
            dialog.dismiss()
        }
    }
}