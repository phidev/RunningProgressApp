package com.phidev.runningprogressapp.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.phidev.runningprogressapp.R
import com.phidev.runningprogressapp.data.ProgressViewModel
import com.phidev.runningprogressapp.data.entity.Progress
import com.phidev.runningprogressapp.databinding.FragmentInputResultsActivityBinding
import java.util.*

class InputResultsActivity : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var inputResultsActivityBinding: FragmentInputResultsActivityBinding
    private lateinit var mProgressViewModel: ProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inputResultsActivityBinding =
            FragmentInputResultsActivityBinding.inflate(inflater, container, false)

        return inputResultsActivityBinding.root

    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        mProgressViewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)

        // Initialisieren von Variablen für Date- und TimeDialog.
        var year = 0
        var month = 0
        var dayOfMonth = 0
        val hours = 0
        val minutes = 0

        fun getDateAndTimeCalendar() {
            val cal = Calendar.getInstance()
            year = cal.get(Calendar.YEAR)
            month = cal.get(Calendar.MONTH)
            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        }

        inputResultsActivityBinding.textInputDate.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputDate.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                DatePickerDialog(requireActivity(), this, year, month, dayOfMonth).show()
            }
            inputResultsActivityBinding.textInputDate.clearFocus()
        }

        inputResultsActivityBinding.textInputDuration.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputDuration.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                TimePickerDialog(requireActivity(), this, hours, minutes, true)
                    .show()
            }
            inputResultsActivityBinding.textInputDuration.clearFocus()
        }

        inputResultsActivityBinding.buttonSubmit.setOnClickListener {
            if (inputResultsActivityBinding.textInputDate.text.isNullOrEmpty() ||
                inputResultsActivityBinding.textInputDuration.text.isNullOrEmpty() ||
                        !(validateForm(inputResultsActivityBinding.textInputDistance.text.toString()))
            ) {
                Snackbar.make(view, R.string.invalid_input, Snackbar.LENGTH_LONG).show()

            } else {
                insertDataToDB()
                Snackbar.make(view, R.string.success_validation, Snackbar.LENGTH_LONG).show()
                inputResultsActivityBinding.textInputDate.text?.clear()
                inputResultsActivityBinding.textInputDistance.text?.clear()
                inputResultsActivityBinding.textInputDistance.clearFocus()
                inputResultsActivityBinding.textInputDuration.text?.clear()
                inputResultsActivityBinding.textInputDuration.clearFocus()
                findNavController().navigate(R.id.action_inputResultsActivity_to_homeFragmentActivity)
            }

        }

    }

    private fun insertDataToDB() {
        val date = inputResultsActivityBinding.textInputDate.text.toString()
        val distance = inputResultsActivityBinding.textInputDistance.text.toString()
        val duration = inputResultsActivityBinding.textInputDuration.text.toString()

        val progress = Progress(0, date, distance, duration)
        mProgressViewModel.addProgress(progress)
    }

    private fun validateForm(distance: String?): Boolean {
        val regex = "^(?![,.])(\\d{0,})([,.]{0,1})(\\d{0,})([^,.]){1,7}\$".toRegex()
        val matchResult = distance?.let { regex.matches(it) }
        return distance != null && matchResult == true
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance()
        .newEditable(this)

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val correctMonth = month + 1
        val resultDate = "$dayOfMonth.$correctMonth.$year"
        inputResultsActivityBinding.textInputDate.text = resultDate.toEditable()
    }

    override fun onTimeSet(p0: TimePicker?, hours: Int, minutes: Int) {
        val resultTime = "$hours:$minutes"
        inputResultsActivityBinding.textInputDuration.text = resultTime.toEditable()
    }

}
