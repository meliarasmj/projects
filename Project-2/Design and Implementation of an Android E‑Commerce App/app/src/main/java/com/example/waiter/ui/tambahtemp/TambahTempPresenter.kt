package com.example.waiter.ui.tambahtemp

import android.util.Log
import android.widget.Toast
import com.example.waiter.model.ResponseKategori
import com.example.waiter.model.ResponseMenu
import com.example.waiter.model.ResponsePostTemp
import com.example.waiter.network.InitRetrofit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahTempPresenter {
    var view: TambahTempView

    constructor(view: TambahTempView) {
        this.view = view
    }

    fun postTemp(idUser:String, idMenu:String, jumlah:String, catatan:String) {
        val api = InitRetrofit().getInitInstance()
        api.posttemp(idUser,idMenu,jumlah,catatan).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("responseses", response.toString())
                if (response.isSuccessful) {
                    val gson = Gson()
                    var jsonString = gson.toJson(response.body())
                    val obj = JSONObject(jsonString)
                    var status = obj.get("status")

                    val responseData = object : TypeToken<ResponsePostTemp>() {}.type
                    val listData = gson.fromJson<ResponsePostTemp>(jsonString, responseData)

                    if (status as Boolean) {
                        view.onSuccessPostTemp(listData.message)
                    }else{
                        view.onSuccessPostTemp(listData.message)
                    }

                }else{
                    view.onErrorResponse()
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                Log.e("android1", "errornya ${error.message}")
            }
        })
    }
}