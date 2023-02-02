package com.example.mychat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychat.adapter.ContactAdapter
import com.example.mychat.data.Repository
import com.example.mychat.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = ContactAdapter()
        binding.chatRecycler.adapter = adapter
        binding.chatRecycler.layoutManager = LinearLayoutManager(requireContext())

        val contacts = Repository().loadContacts()
        adapter.submitList(contacts)
    }

}