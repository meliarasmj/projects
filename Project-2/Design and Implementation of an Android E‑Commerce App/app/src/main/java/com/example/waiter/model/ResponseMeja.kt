package com.example.waiter.model

data class ResponseMeja(
    val message: String,
    val payload: List<PayloadMeja>,
    val status: Boolean
)