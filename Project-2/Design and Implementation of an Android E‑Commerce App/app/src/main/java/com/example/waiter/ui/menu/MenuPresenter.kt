package com.example.waiter.ui.menu

import android.util.Log
import com.example.waiter.model.ResponseKategori
import com.example.waiter.model.ResponseMenu
import com.example.waiter.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuPresenter {
    var view: MenuView

    constructor(view: MenuView) {
        this.view = view
    }

    fun getResponse(idKategori:String, q:String) {
        val api = InitRetrofit().getInitInstance()
        api.getMenu(idKategori,q).enqueue(object : Callback<ResponseMenu> {
            override fun onResponse(call: Call<ResponseMenu>, response: Response<ResponseMenu>) {
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

            override fun onFailure(call: Call<ResponseMenu>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}