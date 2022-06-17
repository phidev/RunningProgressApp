package com.phidev.runningprogressapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.phidev.runningprogressapp.R
import com.phidev.runningprogressapp.databinding.FragmentHomeActivityBinding


class HomeFragmentActivity : Fragment() {
    private lateinit var homeFragmentHomeActivityBinding: FragmentHomeActivityBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        homeFragmentHomeActivityBinding =
            FragmentHomeActivityBinding.inflate(inflater, container, false)
        return homeFragmentHomeActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireContext().getSharedPreferences("namePreference", Context.MODE_PRIVATE)

        checkIfNameIsSet(sharedPreferences)

        homeFragmentHomeActivityBinding.buttonInputResult.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragmentActivity_to_inputResultsActivity)

        }
        homeFragmentHomeActivityBinding.buttonShowResults.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragmentActivity_to_showResultsActivity)
        }
    }

    private fun checkIfNameIsSet(sharedPreferences: SharedPreferences) {
        val userName = sharedPreferences.getString("name", null)
        if (userName.isNullOrEmpty()) {
            changeNameDialog(sharedPreferences)
        } else { showGreetingName(sharedPreferences) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.buttonAbout) {
            aboutInfoDialog()
        }

        if (item.itemId == R.id.changeNameDialog) {
            val sharedPreferences =
                requireContext().getSharedPreferences("namePreference", Context.MODE_PRIVATE)
            changeNameDialog(sharedPreferences)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun changeNameDialog(sharedPreferences: SharedPreferences) {
        val write = sharedPreferences.edit()
        val etNeu = EditText(requireContext())
        val name = etNeu.text
        val showDialog = AlertDialog.Builder(requireContext())

        showDialog.setIcon(R.drawable.ic_baseline_edit_24)
        showDialog.setTitle(R.string.name_alert_question)
        showDialog.setPositiveButton(R.string.save_alert_button) { _, _ ->
            write.apply {
                write.putString("name", "Hi, $name ✌")
                apply()
            }
            showGreetingName(sharedPreferences)
        }
        showDialog.setNegativeButton(R.string.cancel_alert_button) { _, _ -> }
        showDialog.setView(etNeu)
        showDialog.show()
    }

    private fun showGreetingName(sharedPreferences: SharedPreferences) {
        val userName = sharedPreferences.getString("name", null)
        homeFragmentHomeActivityBinding.textViewGreeting.text = userName.toString()
    }

    private fun aboutInfoDialog() {
        val aboutAuthor = resources.getString(R.string.about_author)
        val aboutVersionNumber = resources.getString(R.string.about_version_number)
        val aboutAuthorName = resources.getString(R.string.about_author_name)
        val showDialog = AlertDialog.Builder(requireContext())

        showDialog.setIcon(R.drawable.ic_baseline_info_24)
        showDialog.setTitle(R.string.about_alert_title)
        showDialog.setMessage("$aboutVersionNumber\n$aboutAuthor: $aboutAuthorName")
        showDialog.setPositiveButton("OK") { _, _ -> }
        showDialog.show()
    }
}