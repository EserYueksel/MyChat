package com.example.mychat.data

import com.example.mychat.R
import com.example.mychat.data.model.Contact

class Repository {

    fun loadContacts(): List<Contact> {
        return  listOf(
            Contact(
                1,
                "Peter Griffin",
                R.drawable.contactpeter,
                mutableListOf(),
                mutableListOf()
            ),
            Contact(
                2,
                "Glenn Quagmire",
                R.drawable.contactquagmire,
                mutableListOf(),
                mutableListOf()
            )
        )
    }
}