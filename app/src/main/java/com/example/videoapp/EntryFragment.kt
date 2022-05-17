package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_logIn = view.findViewById<Button>(R.id.btnLogin)
        val btn_singUp = view.findViewById<Button>(R.id.registr_btn)
        btn_logIn.setOnClickListener {
            findNavController().navigate(
                EntryFragmentDirections.actionEntryFragmentToRegistrateFragment()
            )
        }
        btn_singUp.setOnClickListener {
            findNavController().navigate(
                EntryFragmentDirections.actionEntryFragmentToSingUpFragment()
            )
        }

    }
}