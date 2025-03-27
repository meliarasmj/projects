package com.example.waiter.ui.detailtransaksi

import com.example.waiter.model.PayloadDetailTransaksi

interface DetailTransaksiView {
    fun onSuccess(payloadDetailTransaksi: List<PayloadDetailTransaksi>, total: String, status:String, pembayaran:String, foto:String, ongkir:String, subtotal:String)
    fun onErrorResponse()
}