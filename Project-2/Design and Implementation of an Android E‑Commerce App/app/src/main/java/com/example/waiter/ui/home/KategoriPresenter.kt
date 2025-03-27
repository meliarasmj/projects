package com.example.waiter.ui.home

import android.util.Log
import com.example.waiter.model.ResponseKategori
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriPresenter {
    var view: KategoriView

    constructor(view: KategoriView) {
        this.view = view
    }

    fun getResponse() {
        val api = InitRetrofit().getInitInstance()
        api.getKategori().enqueue(object : Callback<ResponseKategori> {
            override fun onResponse(call: Call<ResponseKategori>, response: Response<ResponseKategori>) {
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

            override fun onFailure(call: Call<ResponseKategori>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}