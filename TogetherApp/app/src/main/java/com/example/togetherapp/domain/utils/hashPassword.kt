package com.example.togetherapp.domain.utils

import java.security.MessageDigest

fun hashPassword(password: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(password.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}
