package com.example.kopilka.ui.format

import com.example.kopilka.model.Currency
import java.text.NumberFormat
import java.util.Locale

fun formatMoney(minor: Long, currency: Currency): String {
    val amount = minor.toDouble() / 100.0
    val fmt = NumberFormat.getCurrencyInstance(Locale.getDefault())
    fmt.currency = java.util.Currency.getInstance( when(currency){
        Currency.UAH -> "UAH"
        Currency.RUB -> "RUB"
        Currency.USD -> "USD"
    })
    return fmt.format(amount)
}
