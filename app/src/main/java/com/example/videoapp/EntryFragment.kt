package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
            Navigation.findNavController(view)
                .navigate(R.id.action_entryFragment_to_registrateFragment)
        }

        binding.singUpBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_entryFragment_to_singUpFragment)
        }

    }
}