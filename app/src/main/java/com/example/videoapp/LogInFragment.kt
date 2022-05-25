package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.logInBtn.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.edtEmail.text.toString(),
                binding.passwordEdt.text.toString()
            ).addOnCompleteListener { Task ->
                if (Task.isSuccessful) {
                    findNavController().navigate(
                        LogInFragmentDirections.actionRegistrateFragmentToGeneralFragment()
                    )
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
