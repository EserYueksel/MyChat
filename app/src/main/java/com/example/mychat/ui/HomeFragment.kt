package com.example.mychat.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mychat.adapter.ContactAdapter
import com.example.mychat.SharedViewModel
import com.example.mychat.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val contactAdapter = ContactAdapter()
        binding.homeRv.adapter = contactAdapter

        viewModel.contactList.observe(viewLifecycleOwner) {
            contactAdapter.submitList(it)
        }
    }
}