package com.example.thequiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.thequiz.databinding.ActivityQuizAttemptMainBinding
import com.example.thequiz.databinding.ActivityQuizAttemptMainBinding.*
import com.example.thequiz.databinding.ActivitySaveQuestionsBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class QuizAttemptMain : AppCompatActivity(),View.OnClickListener{
    private lateinit var binding:ActivityQuizAttemptMainBinding
    lateinit var ref: DatabaseReference
    val mQuestionsList=ArrayList<questions>()
    var n=0
    val size=mQuestionsList.size
    private var mCurrentPosition: Int = 1 // Default and the first question positionl

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= inflate(layoutInflater)
        setContentView(binding.root)


        FirebaseApp.initializeApp(this)

        val mUserName=intent.getStringExtra("creatorname")
        ref= FirebaseDatabase.getInstance().reference.child("Quiz").child(mUserName.toString())
        ref.child("n").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                n= snapshot.getValue() as Int
                getquestions()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)



    }
    private fun getquestions() {

        for(i in 1..n+1){
            ref.addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var a:questions = snapshot.child(i.toString()).value as questions
                    mQuestionsList.add(a)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv_option_one -> {

                selectedOptionView(binding.tvOptionOne, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(binding.tvOptionTwo, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(binding.tvOptionThree, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(binding.tvOptionFour, 4)
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                            // START
                            val intent =
                                Intent(this@QuizAttemptMain, Result::class.java)
                            intent.putExtra("name", mUserName)
                            intent.putExtra("correctans", mCorrectAnswers)
                            intent.putExtra("size", mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                            // END
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctanswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctanswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun setQuestion() {

        val question = mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + "$n"

        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.option1
        binding.tvOptionTwo.text = question.option2
        binding.tvOptionThree.text = question.option3
        binding.tvOptionFour.text = question.option4
    }
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizAttemptMain,
                R.drawable.default_option_border_bg
            )
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this@QuizAttemptMain,
                    drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this@QuizAttemptMain,
                    drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this@QuizAttemptMain,
                    drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this@QuizAttemptMain,
                    drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizAttemptMain,
            R.drawable.selected_option_border_bg
        )
    }
}