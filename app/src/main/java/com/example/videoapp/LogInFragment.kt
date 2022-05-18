package com.example.videoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class LogInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val firebaseDatabase = FirebaseFirestore.getInstance()
        val logIn_btn = view.findViewById<Button>(R.id.logIn_btn)

        val email_edt = view.findViewById<EditText>(R.id.edt_email)
        val password_edt = view.findViewById<EditText>(R.id.password_edt)

        logIn_btn.setOnClickListener {
            auth.signInWithEmailAndPassword(
                email_edt.text.toString(),
                password_edt.text.toString()
            ).addOnCompleteListener { Task ->
                if (Task.isSuccessful) {
                    findNavController().navigate(
                        LogInFragmentDirections.actionRegistrateFragmentToHomeFragment()
                    )
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
