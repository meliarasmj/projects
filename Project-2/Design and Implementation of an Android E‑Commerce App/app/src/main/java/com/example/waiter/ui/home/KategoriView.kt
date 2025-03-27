package com.example.waiter.ui.home

import com.example.waiter.model.PayloadKategori

interface KategoriView {
    fun onSuccess(payloadKategori: List<PayloadKategori>)
    fun onErrorResponse()
}