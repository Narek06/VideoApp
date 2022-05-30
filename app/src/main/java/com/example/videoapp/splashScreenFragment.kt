package com.example.videoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class splashScreenFragment : Fragment() {

    private val activityScope = CoroutineScope(Dispatchers.Main)
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        activityScope.launch {
            delay(3000)
            if (auth.currentUser != null) {
                findNavController().navigate(
                    splashScreenFragmentDirections
                        .actionStartFragmentToGeneralFragment()
                )
            } else {
                findNavController().navigate(
                    splashScreenFragmentDirections
                        .actionStartFragmentToEntryFragment()
                )
            }
        }
    }
}