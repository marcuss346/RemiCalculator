package com.example.remicalculator.Data

import kotlinx.serialization.Serializable


@Serializable
data class Translation (
    val from: String,
    val to: String,
    val text: String,
)

@Serializable
data class TranslationResponse(
    val trans: String,
    val source_language_code: String,
    val source_language: String,
    val trust_level: Int
)