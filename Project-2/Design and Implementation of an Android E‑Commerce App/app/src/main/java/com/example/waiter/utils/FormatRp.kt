package com.example.waiter.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class FormatRp {
    companion object {
        fun parsingRupiah(number: Int): String {
            val mataUangIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
            mataUangIndonesia.minimumFractionDigits = 0
            val formatRp = DecimalFormatSymbols()
            formatRp.currencySymbol = "Rp. "
            formatRp.groupingSeparator = '.'
            mataUangIndonesia.decimalFormatSymbols = formatRp
            return mataUangIndonesia.format(number)
        }
    }
}