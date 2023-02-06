package com.example.mychat.data

import com.example.mychat.R
import com.example.mychat.data.model.Contact


/**
 * Diese Klasse sorgt für die Aufbereitung der Daten
 */
class Repository {

    /**
     * Diese Funktion gibt eine Liste aus Contact Objekten zurück.
     * Jedes Contact Objekt enthält die Informationen für den Namen und
     * die Bild Ressource und eine leere veränderliche Liste
     */
    fun loadContacts(): List<Contact> {
        return listOf(
            Contact(1, "Peter Griffin", R.drawable.contactpeter, mutableListOf()),
            Contact(2, "Emma", R.drawable.contactpeter, mutableListOf())
        )
    }
}
