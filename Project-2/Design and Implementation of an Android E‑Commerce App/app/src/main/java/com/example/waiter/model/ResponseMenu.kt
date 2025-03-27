package com.example.waiter.model

data class ResponseMenu(
    val message: String,
    val payload: List<PayloadMenu>,
    val status: Boolean
)