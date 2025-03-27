package com.example.waiter.model

data class ResponseTemp(
        val message: String,
        val payload: List<PayloadTemp>,
        val status: Boolean,
        val total: String
)