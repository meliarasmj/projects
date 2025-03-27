package com.example.waiter.model

data class PayloadDetailTransaksi(
    val catatan: String,
    val foto: String,
    val harga: String,
    val id_jumlah_menu: String,
    val id_menu: String,
    val id_transaksi: String,
    val jumlah: String,
    val nama_menu: String,
    val subTotal: String
)