package com.phidev.runningprogressapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phidev.runningprogressapp.databinding.FragmentShowResultsActivityBinding

class ShowResultsActivity : Fragment() {
    private lateinit var showResultsActivityBinding: FragmentShowResultsActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showResultsActivityBinding =
            FragmentShowResultsActivityBinding.inflate(inflater, container, false)
        return showResultsActivityBinding.root
    }

}