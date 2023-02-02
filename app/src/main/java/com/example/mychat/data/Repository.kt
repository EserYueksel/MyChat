package com.example.mychat.data

import com.example.mychat.R
import com.example.mychat.data.model.Contact

class Repository {

    fun loadContacts(): List<Contact> {
        return  listOf(
            Contact(
                "Peter Griffin",
                R.drawable.contactpeter,
                mutableListOf()
            ),
            Contact(
                "Glenn Quagmire",
                R.drawable.contactquagmire,
                mutableListOf()
            )
        )
    }
}