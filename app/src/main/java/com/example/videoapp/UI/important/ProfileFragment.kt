package com.example.videoapp.UI.important

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoapp.R
import com.example.videoapp.UI.singIn.SingInFragment
import com.example.videoapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment(), View.OnClickListener {
    private val fireStoreDatabase = FirebaseFirestore.getInstance()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.logOut.setOnClickListener(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readData()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.log_out -> {
                val singInFragment = SingInFragment()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView2, singInFragment)
                    ?.commit()
            }
        }
    }

    private fun readData() {
        val auth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            fireStoreDatabase.collection("users")
                .document(auth.currentUser!!.uid).get()
                .addOnSuccessListener { querySnapshot ->
                    val dataFirstName = querySnapshot.data?.get("Name").toString()
                    binding.textView2.text = dataFirstName
                }
            val name: String = user.email.toString()
            binding.textView3.text = name
        }
    }
}