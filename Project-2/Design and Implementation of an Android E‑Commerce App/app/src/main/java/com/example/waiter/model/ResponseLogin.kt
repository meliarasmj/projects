package com.example.waiter.model

data class ResponseLogin(
    val message: String,
    val payload: PayloadLogin,
    val status: Boolean
)