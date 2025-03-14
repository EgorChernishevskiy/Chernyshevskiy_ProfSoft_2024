package com.example.togetherapp.data.utils

import com.example.togetherapp.data.model.auth.LoginRequest
import com.example.togetherapp.data.model.auth.RegisterRequest

fun LoginRequest.toQueryMap(): Map<String, String> {
    return mapOf(
        "email" to email,  // Маппим phone -> email
        "password" to passwordHashed // Маппим passwordHashed -> password
    )
}

fun RegisterRequest.toQueryMap(): Map<String, String> {
    return mapOf(
        "name" to name,
        "surname" to surname,
        "email" to email,  // Маппим phone -> email
        "password" to passwordHashed // Маппим passwordHashed -> password
    )
}