package com.example.waiter.model

data class ResponseDetailTransaksi(
        val message: String,
        val payload: List<PayloadDetailTransaksi>,
        val status: Boolean,
        val status_transaksi: String,
        val subtotal: String,
        val ongkir: String,
        val total: String,
        val pembayaran: String,
        val foto: String,
)