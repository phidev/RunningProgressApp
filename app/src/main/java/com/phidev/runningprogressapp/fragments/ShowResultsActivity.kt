package com.phidev.runningprogressapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.phidev.runningprogressapp.data.ProgressViewModel
import com.phidev.runningprogressapp.databinding.FragmentShowResultsActivityBinding
import com.phidev.runningprogressapp.list.ListAdapter

class ShowResultsActivity : Fragment() {
    private lateinit var showResultsActivityBinding: FragmentShowResultsActivityBinding
    private lateinit var mProgressViewModel: ProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        showResultsActivityBinding =
            FragmentShowResultsActivityBinding.inflate(inflater, container, false)
        val adapter = ListAdapter()
        val recyclerView = showResultsActivityBinding.rvRunningResults
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mProgressViewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)

        mProgressViewModel.readAllData.observe(viewLifecycleOwner) { progress ->
            adapter.setData(progress)
        }

        return showResultsActivityBinding.root
    }



}