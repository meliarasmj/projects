package com.example.waiter.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waiter.R
import com.example.waiter.adapter.KategoriAdapter
import com.example.waiter.databinding.FragmentHomeBinding
import com.example.waiter.model.PayloadKategori
import com.example.waiter.ui.daftarpesanan.DaftarPesananActivity
import com.example.waiter.ui.menuall.MenuAllActivity

class FragmentHome : Fragment(R.layout.fragment_home), KategoriView {
    private var bindings: FragmentHomeBinding? = null
    private lateinit var kategoriPresenter: KategoriPresenter
    private lateinit var kategoriAdapter: KategoriAdapter
    private var idUser = ""
    private var namaUser = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        bindings = binding

        val pref: SharedPreferences = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
        idUser = pref.getString("code", null).toString()
        namaUser = pref.getString("nama", null).toString()

        binding.tvNama.text = "Hallo ${namaUser.split("\\s".toRegex())[0]}"
        binding!!.recycler.isFocusable = false
        binding.ivFoto.setOnClickListener{
            val myIntent = Intent(requireContext(), DaftarPesananActivity::class.java)
            startActivity(myIntent)
        }

        binding.tvDetailMenu.setOnClickListener{
            val myIntent = Intent(requireContext(), MenuAllActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnPesan.setOnClickListener{
            val myIntent = Intent(requireContext(), MenuAllActivity::class.java)
            startActivity(myIntent)
        }


        kategoriPresenter = KategoriPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        kategoriPresenter.getResponse()
    }

    override fun onSuccess(payloadKategori: List<PayloadKategori>) {
        Log.d("payloadKategori", payloadKategori.toString())
//        bindings!!.recycler.visibility = View.VISIBLE
//        bindings!!.pb1.visibility = View.GONE
        kategoriAdapter = KategoriAdapter(requireContext(), payloadKategori as ArrayList<PayloadKategori>)
        bindings?.recycler?.adapter = kategoriAdapter
        bindings?.recycler?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onErrorResponse() {

    }
}