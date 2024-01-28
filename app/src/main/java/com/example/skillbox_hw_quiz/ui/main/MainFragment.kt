package com.example.skillbox_hw_quiz.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.MainFragmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
private const val DATE_TAG = "Date"

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd-MM-yy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater)

        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainFragment_to_questionsFragment)
        }

        binding.buttonDate.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.Date))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMills ->
                calendar.timeInMillis = timeInMills
                Snackbar.make(binding.buttonDate, dateFormat.format(calendar.time) , Snackbar.LENGTH_SHORT).show()
            }

            dateDialog.show(parentFragmentManager,DATE_TAG)

        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}