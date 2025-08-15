package com.example.kopilka.model

enum class Currency(val code: String, val symbol: String) {
    UAH("UAH", "₴"),
    RUB("RUB", "₽"),
    USD("USD", "$");
    companion object {
        fun fromCode(code: String?): Currency =
            entries.firstOrNull { it.code == code } ?: UAH
    }
}
