package com.example.mychat.data.model

data class Contact(
    val contactName: String,
    val imageResourceId: Int,
    val chat: MutableList<String>
)
