package com.example.waiter.ui.menuall

import com.example.waiter.model.PayloadKategori
import com.example.waiter.model.PayloadMenu

interface MenuAllView {
    fun onSuccess(payloadMenu: List<PayloadMenu>)
    fun onErrorResponse()
}