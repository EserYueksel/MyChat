package com.example.mychat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychat.data.Repository
import com.example.mychat.data.model.Contact
import com.example.mychat.data.model.Messages
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Das ViewModel kümmert sich um die Zustände und die Logik der App
 */
class SharedViewModel : ViewModel() {

    // Eine Instanz des Repository wird in einer Variablen gespeichert
    private val repository = Repository()

    // Die Liste aus Kontakten wird in einer verschachtelten Variable gespeichert
    private val _contactList = MutableLiveData<List<Contact>>(repository.loadContacts())
    val contactList: LiveData<List<Contact>>
        get() = _contactList

    // Die Liste an ChatNachrichten wird in einer verschachtelten Variable gespeichert
    private val _chat = MutableLiveData<MutableList<Messages>>(mutableListOf())
    val chat: LiveData<MutableList<Messages>>
        get() = _chat

    /**
     * Diese Funktion initialisiert den Chat und setzt die Variablen dementsprechend
     */
    fun loadChat(id: Int) {
        val contact = contactList.value?.find { it.id == id }

        if (contact != null) {
            _chat.value = contact.chatHistory
        }
    }

    /**
     * Diese Funktion "schickt die Draft Message ab",
     * indem die Variablen dementsprechend gesetzt werden
     */
    fun sendMessage(text: String) {
        val newMessage = Messages(text, false)
        _chat.value?.add(0, newMessage)
        _chat.value = chat.value

        viewModelScope.launch {
            delay(5000)
            generateAnswer()
        }
    }

    /**
     * BONUS
     */

    private fun generateAnswer() {
        val random = (1..5).random()

        val answer = when (random) {
            1 -> "okay. tell me more ;)"
            2 -> "leave me alone dude!!!"
            3 -> "nice to hear that!\nHow are you?"
            4 -> "haha"
            5 -> "I totally get it ;)"
            else -> ".."
        }

        val newMessage = Messages(answer, true)
        _chat.value?.add(0, newMessage)

        _chat.value = chat.value
    }
}
