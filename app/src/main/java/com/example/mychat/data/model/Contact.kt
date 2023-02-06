package com.example.mychat.data.model


/**
 * Diese Data Klasse steht für einen einzelnen Kontakt im HomeFragment
 * @param name der Name des Kontakts
 * @param imageResId die resource ID des Bildes des Kontakts
 * @param chatHistory eine Liste aus Message Objekten, in der der Chat Verlauf gespeichert ist
 */
data class Contact(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val chatHistory: MutableList<Messages>
)
