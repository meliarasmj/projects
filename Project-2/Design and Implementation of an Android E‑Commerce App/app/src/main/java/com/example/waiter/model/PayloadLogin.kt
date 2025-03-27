package com.example.waiter.model

data class PayloadLogin(
    val id: String,
    val nama: String,
    val no_hp: String,
    val password: String,
    val tgl_daftar: String,
    val token: String
)