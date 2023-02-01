package com.example.mychat.data.model

data class Contact(
    val id: Int,
    val contactName: String,
    val imageResourceId: Int,
    val chatAnswers: MutableList<String>,
    val chatHistory: MutableList<String>

)
