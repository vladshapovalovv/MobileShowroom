package com.example.mobileshowroom.model.ui.navigation

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import com.example.mobileshowroom.R
import com.example.mobileshowroom.databinding.FragmentProfileBinding
import com.example.mobileshowroom.utils.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}