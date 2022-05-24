package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

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

//        binding.settingTv.setOnClickListener {
//            findNavController().navigate(
//                ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
//            )
//        }
        binding.aboutTv.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToAboutFragment()
            )
        }
        binding.settingsBtn.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
            )
        }
        binding.aboutBtn.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToAboutFragment()
            )
        }
    }
}