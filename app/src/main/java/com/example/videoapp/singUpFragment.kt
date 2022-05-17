package com.example.videoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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

    override fun onStart() {
        super.onStart()
        Timber.plant(Timber.DebugTree())
        // Check if user is signed in (non-null) and update UI accordingly.
        if (auth.currentUser != null) {
            Timber.e("Welcome app")
            // FirebaseAuth.getInstance().signOut()
            readUserData(auth.currentUser!!.uid)
        } else {
            login("judlg@gmail.com", "123456")
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
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

    fun registration(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    //Timber.e("Success registration ${user?.uid}")
                    createNewUser()
                } else {
                    //Timber.e("Error registration")
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