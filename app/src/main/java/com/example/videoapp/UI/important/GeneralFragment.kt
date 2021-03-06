package com.example.videoapp.UI.important

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoapp.R
import com.example.videoapp.databinding.FragmentGeneralBinding

class GeneralFragment : Fragment() {
    private lateinit var binding: FragmentGeneralBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGeneralBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeFragment = HomeFragment()
        val favorite = PhotosFragment()
        val profile = ProfileFragment()

        openFragment(homeFragment)

        binding.menu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    openFragment(homeFragment)
                }
                R.id.photosFragment -> {
                    openFragment(favorite)
                }
                R.id.profileFragment -> {
                    openFragment(profile)
                }
            }
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainerView2, fragment)
            commit()
        }
    }
}
