package com.example.mychat.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mychat.SharedViewModel
import com.example.mychat.adapter.MessageAdapter
import com.example.mychat.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    // Hier wird das ViewModel, in dem die Logik stattfindet, geholt
    private val viewModel: SharedViewModel by activityViewModels()

    // Das binding für das QuizFragment wird deklariert
    private lateinit var binding: FragmentChatBinding

    /**
     * Lifecycle Funktion onCreateView
     * Hier wird das binding initialisiert und das Layout gebaut
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     * Hier werden die Elemente eingerichtet und z.B. onClickListener gesetzt
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Das übergebene Argument ("contact Index") wird in eine Variable gespeichert
        val contactId = requireArguments().getInt("contactId")

        // Über die Funktion aus dem ViewModel wird der Chat initialisiert
        viewModel.loadChat(contactId)

        // hier finden wir über die contactId den richtigen Contact aus der contactList
        // um den Namen zu setzen
        viewModel.contactList.observe(viewLifecycleOwner) { list ->
            val contact = list.find { it.id == contactId }

            if (contact != null) {
                binding.chatToolbar.title = contact.name
            }
        }

        binding.chatToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val messageAdapter = MessageAdapter()
        binding.rvMessages.adapter = messageAdapter

        viewModel.chat.observe(
            viewLifecycleOwner,
            Observer {
                messageAdapter.submitList(it)
            }
        )

        // Der btnSend bekommt einen Click Listener
        binding.btnSend.setOnClickListener {
            val text = binding.textInput.text.toString()
            if (text == "") {
                Toast.makeText(context, "Bitte zuerst eine Nachricht eingeben!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.sendMessage(text)
                binding.textInput.setText("")

                // mit diesen Zeilen wird das Softkeyboard wieder versteckt
                val imm: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}
