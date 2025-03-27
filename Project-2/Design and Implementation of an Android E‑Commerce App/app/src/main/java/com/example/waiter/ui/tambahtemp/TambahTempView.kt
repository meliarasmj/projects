package com.example.waiter.ui.tambahtemp

import com.example.waiter.model.PayloadMenu

interface TambahTempView {
    fun onSuccessPostTemp(message: String)
    fun onErrorResponse()
}