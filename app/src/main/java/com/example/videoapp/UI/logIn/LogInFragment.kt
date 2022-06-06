package com.example.videoapp.UI.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.videoapp.interfaceis.Keyboard
import com.example.videoapp.R
import com.example.videoapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment(), Keyboard {
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

        binding.loginBtn.setOnClickListener {
            activity?.hideKeyeboard()
            if (binding.edtEmail.text.isNotEmpty() && binding.passwordEdt.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    binding.edtEmail.text.toString(),
                    binding.passwordEdt.text.toString()
                ).addOnCompleteListener { Task ->
                    if (Task.isSuccessful) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_registrateFragment_to_generalFragment)
                    } else {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
