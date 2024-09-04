package com.example.togetherapp.presentation.utils

fun formatPhoneNumber(phoneNumber: String?): String {
    return phoneNumber?.replace(Regex("[^\\d]"), "")?.let {
        when (it.length) {
            10 -> "+7 (${it.substring(0, 3)}) ${it.substring(3, 6)}-${it.substring(6, 8)}-${it.substring(8, 10)}"
            11 -> "+7 (${it.substring(1, 4)}) ${it.substring(4, 7)}-${it.substring(7, 9)}-${it.substring(9, 11)}"
            else -> phoneNumber
        }
    } ?: ""
}