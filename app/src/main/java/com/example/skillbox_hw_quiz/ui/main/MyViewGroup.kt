package com.example.skillbox_hw_quiz.ui.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.MainActivityBinding
import com.example.skillbox_hw_quiz.databinding.MyViewGroupBinding
import com.example.skillbox_hw_quiz.quiz.Question
import com.example.skillbox_hw_quiz.quiz.QuizStorage

class MyViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: MyViewGroupBinding
    var answer: Int? = null

    init {
        val inflatedView = inflate(context, R.layout.my_view_group, this)
        binding = MyViewGroupBinding.bind(inflatedView)

        binding.radioGroup.setOnCheckedChangeListener{ group , button ->
            Log.d("GROUP","button $button")
            when(button){
                binding.radioButton.id ->{
                    Log.d("GROUP" , "1 button")
                    answer = 1
                }
                binding.radioButton2.id -> {
                    Log.d("GROUP" , "2 button")
                    answer = 2
                }
                binding.radioButton3.id -> {
                    Log.d("GROUP" , "3 button")
                    answer = 3
                }
                binding.radioButton4.id -> {
                    Log.d("GROUP" , "4 button")
                    answer = 4
                }
            }
        }
    }

    fun setTopText(text: String) {
        binding.topText.text = text
    }

    fun setAnswers(
        list: List<String>
    ) {
        binding.radioButton.text = list[0]
        binding.radioButton2.text = list[1]
        binding.radioButton3.text = list[2]
        binding.radioButton4.text = list[3]
    }
}