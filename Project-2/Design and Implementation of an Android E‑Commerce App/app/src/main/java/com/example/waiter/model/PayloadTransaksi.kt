package com.example.waiter.model

data class PayloadTransaksi(
    val estimasi: String,
    val id_meja: String,
    val id_transaksi: String,
    val id_user: String,
    val status: String,
    val foto: String,
    val tgl_transaksi: String,
    val total_bayar: String
)