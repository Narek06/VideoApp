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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class singUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseDatabase = FirebaseFirestore.getInstance()
        val singUp_btn = view.findViewById<Button>(R.id.singUp_btn)

        val name_edt = view.findViewById<EditText>(R.id.edt_name)
        val surname_edt = view.findViewById<EditText>(R.id.edt_surname)
        val email_edt = view.findViewById<EditText>(R.id.edt_email)
        val password_edt = view.findViewById<EditText>(R.id.password_edt)


        singUp_btn.setOnClickListener {
            if (name_edt.text.toString().isEmpty()) {
                Toast.makeText(context, "Name is empty!", Toast.LENGTH_SHORT).show()
            } else if (surname_edt.text.toString().isEmpty()) {
                Toast.makeText(context, "Surname is empty!", Toast.LENGTH_SHORT).show()
            } else if (email_edt.text.toString().isEmpty()) {
                Toast.makeText(context, "Email is empty!", Toast.LENGTH_SHORT).show()
            } else if (password_edt.text.toString().isEmpty()) {
                Toast.makeText(context, "Password is empty!", Toast.LENGTH_SHORT).show()
            } else {
                auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(
                    email_edt.text.toString(),
                    password_edt.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "it's okay", Toast.LENGTH_SHORT).show()
                            val firbaseUser = FirebaseAuth.getInstance().currentUser
                            val hashMap = hashMapOf<String, Any>(
                                "Name" to name_edt.text.toString(),
                                "Surname" to surname_edt.text.toString(),
                                "email" to email_edt.text.toString()
                            )
                            firebaseDatabase.collection("users").document(firbaseUser!!.uid)
                                .set(hashMap)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "add data base", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.e("Success login")
                    val user = auth.currentUser
                    readUserData(user!!.uid)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.e("Error login")
                }
            }
    }

    private fun readUserData(userId: String) {
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                Timber.e("success")
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting documents $exception")
            }
    }

    private fun readAllData() {

        FirebaseUtils().fireStoreDatabase.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    Log.e("TAG", "Read document with ID ${document.id}")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting documents $exception")
            }

    }

    private fun createNewUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val hashMap = hashMapOf<String, Any>(
            "name" to "John doe",
            "city" to "Nairobi",
            "age" to 24
        )
        FirebaseUtils().fireStoreDatabase.collection("users").document(firebaseUser!!.uid)
            .set(hashMap)
            .addOnSuccessListener {
                Log.e("TAG", "Added document with ID ")
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error adding document $exception")
            }
    }
}