package com.phidev.runningprogressapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.phidev.runningprogressapp.R
import com.phidev.runningprogressapp.databinding.FragmentHomeActivityBinding


class HomeFragmentActivity : Fragment() {
    private lateinit var homeFragmentHomeActivityBinding: FragmentHomeActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeFragmentHomeActivityBinding =
            FragmentHomeActivityBinding.inflate(inflater, container, false)
        return homeFragmentHomeActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentHomeActivityBinding.buttonInputResult.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragmentActivity_to_inputResultsActivity)

        }
        homeFragmentHomeActivityBinding.buttonShowResults.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragmentActivity_to_showResultsActivity)
        }
    }

}