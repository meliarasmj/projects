package com.example.waiter.model

data class ResponseTransaksi(
        val message: String,
        val payload: List<PayloadTransaksi>,
        val status: Boolean
)