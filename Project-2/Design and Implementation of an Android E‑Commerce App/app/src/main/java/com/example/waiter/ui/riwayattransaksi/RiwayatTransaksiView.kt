package com.example.waiter.ui.riwayattransaksi

import com.example.waiter.model.PayloadTransaksi

interface RiwayatTransaksiView {
    fun onSuccess(payloadTransaksi: List<PayloadTransaksi>)
    fun onErrorResponse()
}