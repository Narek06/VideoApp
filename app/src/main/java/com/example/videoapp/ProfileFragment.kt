package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.aboutTv.setOnClickListener {
            openFragment()
        }
        binding.aboutBtn.setOnClickListener {
            openFragment()
        }
    }

    private fun openFragment() {
        val fragment = AboutFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView2, fragment)
            ?.commit()
    }
}