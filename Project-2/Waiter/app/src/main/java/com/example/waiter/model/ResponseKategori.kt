package com.example.waiter.model

data class ResponseKategori(
    val message: String,
    val payload: List<PayloadKategori>,
    val status: Boolean
)