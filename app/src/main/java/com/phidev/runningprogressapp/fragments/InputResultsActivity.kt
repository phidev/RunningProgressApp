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
        var hours = 0
        var minutes = 0

        fun getDateAndTimeCalendar() {
            val cal = Calendar.getInstance()
            year = cal.get(Calendar.YEAR)
            month = cal.get(Calendar.MONTH)
            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
            hours = cal.get(Calendar.HOUR)
            minutes = cal.get(Calendar.MINUTE)

        }

        // Nur zu Tetzwecken. Wird im weiteren Verlauf sinvoll erweitert.
        fun validate(editable: Editable?) {
            if (editable != null) if (editable.isNotEmpty()) {
                val toastMessage =
                    Toast.makeText(context, "${R.string.validation}", Toast.LENGTH_SHORT)
                toastMessage.show()
            }
        }

        inputResultsActivityBinding.textInputDate.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputDate.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                DatePickerDialog(requireActivity(), this, year, month, dayOfMonth).show()
            }
        }

        inputResultsActivityBinding.textInputTime.setOnFocusChangeListener { _, b ->
            if (b) {
                inputResultsActivityBinding.textInputTime.setRawInputType(InputType.TYPE_NULL)
                getDateAndTimeCalendar()
                TimePickerDialog(requireActivity(), this, hours, minutes, true).show()
            }
        }

        inputResultsActivityBinding.buttonSubmit.setOnClickListener {
            val foo = inputResultsActivityBinding.textInputDate.text
            validate(foo)
        }

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Nur zu Tetzwecken. Wird im weiteren Verlauf sinvoll erweitert.
        inputResultsActivityBinding.tvHeaderInput.text = "$dayOfMonth.$month.$year"

    }
    // Nur zu Tetzwecken. Wird im weiteren Verlauf sinvoll erweitert.
    override fun onTimeSet(p0: TimePicker?, hours: Int, minutes: Int) {
        inputResultsActivityBinding.tvHeaderInput.text = "$hours:$minutes"

    }

}
