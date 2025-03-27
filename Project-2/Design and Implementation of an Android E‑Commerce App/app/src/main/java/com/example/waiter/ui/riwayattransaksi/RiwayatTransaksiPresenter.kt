package com.example.waiter.ui.riwayattransaksi

import android.util.Log
import com.example.waiter.model.ResponseKategori
import com.example.waiter.model.ResponseMenu
import com.example.waiter.model.ResponseTransaksi
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatTransaksiPresenter {
    var view: RiwayatTransaksiView

    constructor(view: RiwayatTransaksiView) {
        this.view = view
    }

    fun getResponse(idUser:String) {
        val api = InitRetrofit().getInitInstance()
        api.getTransaksi(idUser).enqueue(object : Callback<ResponseTransaksi> {
            override fun onResponse(call: Call<ResponseTransaksi>, response: Response<ResponseTransaksi>) {
                Log.d("responseses", response.toString())
                if (response.isSuccessful) {
                    val jsonresponse = response.body()?.payload
                    if (jsonresponse !=null) {
                          view.onSuccess(jsonresponse)
                    } else {
                        view.onErrorResponse()
                    }
                }else{
                    view.onErrorResponse()
                }
            }

            override fun onFailure(call: Call<ResponseTransaksi>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}