package com.example.skillbox_hw_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuestionsBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val locale: QuizStorage.Locale = if (Locale.getDefault().language == "ru") {
        QuizStorage.Locale.Ru
    } else {
        QuizStorage.Locale.En
    }

    private var quiz = QuizStorage.getQuiz(locale)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater)

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_questionsFragment_to_mainFragment)
        }

        val firstViewGroup = binding.myViewGroup

        firstViewGroup.setTopText(QuizStorage.getQuiz(locale).questions[0].question)
        firstViewGroup.setAnswers(QuizStorage.getQuiz(locale).questions[0].answers)

        firstViewGroup.alpha = 0f
        firstViewGroup.animate().apply {
            duration = 1500
            alpha(1f)
        }.start()

        val secondViewGroup = binding.myViewGroup2
        secondViewGroup.setTopText(QuizStorage.getQuiz(locale).questions[1].question)
        secondViewGroup.setAnswers(QuizStorage.getQuiz(locale).questions[1].answers)
        secondViewGroup.alpha = 0f
        secondViewGroup.animate().apply {
            startDelay = 1000
            duration = 1500
            alpha(1f)
        }.start()

        val thirdViewGroup = binding.myViewGroup3
        thirdViewGroup.setTopText(QuizStorage.getQuiz(locale).questions[2].question)
        thirdViewGroup.setAnswers(QuizStorage.getQuiz(locale).questions[2].answers)
        thirdViewGroup.alpha = 0f
        thirdViewGroup.animate().apply {
            startDelay = 2000
            duration = 1500
            alpha(1f)
        }.start()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {

            if (answersCheck().size < 3) {
                Toast.makeText(context, getString(R.string.toast), Toast.LENGTH_SHORT).show()
            } else {

                val myAnswers = answersCheck().toList()

                val resultString = QuizStorage.answer(quiz, myAnswers)

                val bundle = Bundle().apply {
                    putString(ARG_PARAM1, resultString)
                }

                view.findNavController()
                    .navigate(R.id.action_questionsFragment_to_resultFragment, bundle)
            }
        }
    }

    private fun answersCheck(): MutableList<Int> {
        val res = mutableListOf<Int>()
        val answer1 = binding.myViewGroup.answer
        val answer2 = binding.myViewGroup2.answer
        val answer3 = binding.myViewGroup3.answer

        if (answer1 != null && answer2 != null && answer3 != null) {
            res.add(answer1)
            res.add(answer2)
            res.add(answer3)
        }
        return res
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}