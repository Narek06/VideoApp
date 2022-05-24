package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.videoapp.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class SingUpFragment : Fragment() {
    lateinit var binding: FragmentSingUpBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSingUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseDatabase = FirebaseFirestore.getInstance()

        binding.singUpBtn.setOnClickListener {
            if (binding.edtName.text.toString().isEmpty()) {
                Toast.makeText(context, "Name is empty!", Toast.LENGTH_SHORT).show()
            } else if (binding.edtSurname.text.toString().isEmpty()) {
                Toast.makeText(context, "Surname is empty!", Toast.LENGTH_SHORT).show()
            } else if (validateEmail(binding.edtEmail.text.toString())) {
                binding.edtEmail.error = "Email is not correct!"
            } else {
                if (validatePassword(binding.passwordEdt)) {
                    auth = FirebaseAuth.getInstance()
                    auth.createUserWithEmailAndPassword(
                        binding.edtEmail.text.toString(),
                        binding.passwordEdt.text.toString()
                    )
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "it's okay", Toast.LENGTH_SHORT).show()
                                val firbaseUser = FirebaseAuth.getInstance().currentUser
                                val hashMap = hashMapOf<String, Any>(
                                    "Name" to binding.edtName.text.toString(),
                                    "Surname" to binding.edtSurname.text.toString(),
                                    "email" to binding.edtEmail.text.toString()
                                )
                                firebaseDatabase.collection("users").document(firbaseUser!!.uid)
                                    .set(hashMap)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "add data base", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                findNavController().navigate(
                                    SingUpFragmentDirections.actionSingUpFragmentToGeneralFragment()
                                )
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
    private fun validateEmail(email: String): Boolean {
        val emailRegex = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
        return !emailRegex.matcher(email).matches()
    }
    private fun validatePassword(pass: EditText):Boolean{
        val errorText = when {
            /* Rule 1 */
            !pass.text.contains(Regex("[A-Z]")) -> {
                pass.error = "Password must contain one capital letter"
                false
            }
            /* Rule 2 */
            !pass.text.contains(Regex("[0-9]")) -> {
                pass.error = "Password must contain one digit"
                false
            }
            /* Rule 3, not counting space as special character */
            !pass.text.contains(Regex("[^a-zA-Z0-9 ]")) -> {
                pass.error = "Password must contain one special character"
                false
            }
            else -> true
        }
        return errorText
    }//fun password Validation
}