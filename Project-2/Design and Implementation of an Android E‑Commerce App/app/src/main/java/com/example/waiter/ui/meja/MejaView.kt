package com.example.waiter.ui.meja

import com.example.waiter.model.PayloadKategori
import com.example.waiter.model.PayloadMeja

interface MejaView {
    fun onSuccess(payloadMeja: List<PayloadMeja>)
    fun onErrorResponse()
}