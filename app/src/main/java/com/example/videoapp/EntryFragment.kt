package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentEntryBinding

class EntryFragment : Fragment() {
    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEntryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(
                EntryFragmentDirections.actionEntryFragmentToRegistrateFragment()
            )
        }

        binding.singUpBtn.setOnClickListener {
            findNavController().navigate(
                EntryFragmentDirections.actionEntryFragmentToSingUpFragment()
            )
        }

    }
}