package com.example.waiter.ui.daftarpesanan

import android.util.Log
import com.example.waiter.model.ResponseTemp
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarPesananPresenter {
    var view: DaftarPesananView

    constructor(view: DaftarPesananView) {
        this.view = view
    }

    fun getResponse(idUser:String) {
        val api = InitRetrofit().getInitInstance()
        api.getTemp(idUser).enqueue(object : Callback<ResponseTemp> {
            override fun onResponse(call: Call<ResponseTemp>, response: Response<ResponseTemp>) {
                Log.d("responseses", response.toString())
                if (response.isSuccessful) {
                    val jsonresponse = response.body()?.payload
                    if (jsonresponse !=null) {
                          view.onSuccess(jsonresponse, response.body()!!.total)
                    } else {
                        view.onErrorResponse()
                    }
                }else{
                    view.onErrorResponse()
                }
            }

            override fun onFailure(call: Call<ResponseTemp>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}