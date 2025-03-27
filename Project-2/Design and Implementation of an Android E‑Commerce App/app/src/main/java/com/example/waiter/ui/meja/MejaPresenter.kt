package com.example.waiter.ui.meja

import android.util.Log
import com.example.waiter.model.ResponseKategori
import com.example.waiter.model.ResponseMeja
import com.example.waiter.model.ResponseMenu
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MejaPresenter {
    var view: MejaView

    constructor(view: MejaView) {
        this.view = view
    }

    fun getResponse() {
        val api = InitRetrofit().getInitInstance()
        api.getmeja().enqueue(object : Callback<ResponseMeja> {
            override fun onResponse(call: Call<ResponseMeja>, response: Response<ResponseMeja>) {
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

            override fun onFailure(call: Call<ResponseMeja>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}