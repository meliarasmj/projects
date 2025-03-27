package com.example.waiter.ui.detailtransaksi

import android.util.Log
import com.example.waiter.model.ResponseDetailTransaksi
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTransaksiPresenter {
    var view: DetailTransaksiView

    constructor(view: DetailTransaksiView) {
        this.view = view
    }

    fun getResponse(idTransaksi:String) {
        val api = InitRetrofit().getInitInstance()
        api.getDetailTransaksi(idTransaksi).enqueue(object : Callback<ResponseDetailTransaksi> {
            override fun onResponse(call: Call<ResponseDetailTransaksi>, response: Response<ResponseDetailTransaksi>) {
                Log.d("responseses", response.toString())
                if (response.isSuccessful) {
                    val jsonresponse = response.body()?.payload
                    if (jsonresponse !=null) {
                        view.onSuccess(jsonresponse, response.body()!!.total, response.body()!!.status_transaksi, response.body()!!.pembayaran, response.body()!!.foto, response.body()!!.ongkir, response.body()!!.subtotal)
                    } else {
                        view.onErrorResponse()
                    }
                }else{
                    view.onErrorResponse()
                }
            }

            override fun onFailure(call: Call<ResponseDetailTransaksi>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}