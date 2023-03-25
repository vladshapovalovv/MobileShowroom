package com.example.mobileshowroom.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment(private val layout: Int) : Fragment() {

/*    protected val navController by lazy{
        (activity as MainActivity).navController
    }*/
    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(layout, container, false)
        return rootView
    }

/*    protected val activityViewModel: AttractionsViewModel
        get() = (activity as MainActivity).viewModel*/
}