package com.example.waiter.ui.daftarpesanan

import com.example.waiter.model.PayloadTemp

interface DaftarPesananView {
    fun onSuccess(payloadTemp: List<PayloadTemp>, total: String)
    fun onErrorResponse()
}