package com.example.videoapp.UI.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.videoapp.R
import com.example.videoapp.databinding.SplashScreenFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    lateinit var binding:SplashScreenFragmentBinding
    lateinit var auth: FirebaseAuth
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashScreenFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* GlobalScope.launch(Dispatchers.Default) {
            delay(3000L)
            withContext(Dispatchers.Main) {
                val controller : NavController = Navigation.findNavController(binding.root)
                if (auth.currentUser != null) {
                    controller.navigate(R.id.action_startFragment_to_entryFragment)
                    controller.popBackStack(R.id.action_startFragment_to_entryFragment,false)
                }
                else {
                    controller.navigate(R.id.action_startFragment_to_generalFragment)
                    controller.popBackStack(R.id.action_startFragment_to_generalFragment,false)
                }

            }
        }*/
        activityScope.launch {
            delay(3000)
            val controller: NavController = Navigation.findNavController(binding.root)
            controller.navigate(R.id.action_startFragment_to_entryFragment)
            controller.popBackStack(R.id.action_startFragment_to_generalFragment, false)
        }
    }
}