package com.example.videoapp.UI.singIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.videoapp.R
import com.example.videoapp.databinding.FragmentSingInBinding

class SingInFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSingInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSingInBinding.inflate(inflater)
        binding.btnLogin.setOnClickListener(this)
        binding.singUpBtn.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnLogin -> {
                Navigation.findNavController(view)
                    .navigate(R.id.action_entryFragment_to_registrateFragment)
            }
            R.id.singUp_btn -> {
                Navigation.findNavController(view)
                    .navigate(R.id.action_entryFragment_to_singUpFragment)
            }
        }
    }
}
