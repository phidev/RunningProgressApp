package com.phidev.runningprogressapp.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.phidev.runningprogressapp.R
import com.phidev.runningprogressapp.databinding.FragmentInputResultsActivityBinding
import java.util.*

class InputResultsActivity : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var inputResultsActivityBinding: FragmentInputResultsActivityBinding

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

        fun validate(editable: Editable?) {
            if (editable != null) {
                val regex = "^(?![,.])(\\d{0,})([,.]{0,1})(\\d{0,})([^,.]){1,7}\$".toRegex()
                val matchResult = regex.matches(editable)

                if (!matchResult) {
                    inputResultsActivityBinding.textInputDistance.error = "Ungültige Eingabe"
                    Toast.makeText(context, getString(R.string.error_validation), Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(context, R.string.success_validation, Toast.LENGTH_LONG).show()
                    val res = editable.toString().replace(",", ".").toEditable()
                    inputResultsActivityBinding.textInputDistance.text=res
                    //inputResultsActivityBinding.textInputDistance.text?.clear()
                }

            }
        }

        inputResultsActivityBinding.textInputDate.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputDate.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                DatePickerDialog(requireActivity(), this, year, month, dayOfMonth).show()
            }
            inputResultsActivityBinding.textInputDate.clearFocus()

        }

        inputResultsActivityBinding.textInputTime.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputTime.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                TimePickerDialog(requireActivity(), this, hours, minutes, true)
                    .show()
            }
            inputResultsActivityBinding.textInputTime.clearFocus()
        }

        inputResultsActivityBinding.buttonSubmit.setOnClickListener {
            val validateDistanceInput = inputResultsActivityBinding.textInputDistance.text
            validate(validateDistanceInput)
            inputResultsActivityBinding.textInputDate.text?.clear()
            //inputResultsActivityBinding.textInputDistance.text?.clear()
            inputResultsActivityBinding.textInputDistance.clearFocus()
            inputResultsActivityBinding.textInputTime.text?.clear()
            inputResultsActivityBinding.textInputTime.clearFocus()
        }

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
        inputResultsActivityBinding.textInputTime.text = resultTime.toEditable()
    }
}
