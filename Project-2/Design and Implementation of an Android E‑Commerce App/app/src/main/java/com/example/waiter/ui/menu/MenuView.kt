package com.example.waiter.ui.menu

import com.example.waiter.model.PayloadKategori
import com.example.waiter.model.PayloadMenu

interface MenuView {
    fun onSuccess(payloadMenu: List<PayloadMenu>)
    fun onErrorResponse()
}