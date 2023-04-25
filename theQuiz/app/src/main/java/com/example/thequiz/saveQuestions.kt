package com.example.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.thequiz.databinding.ActivityCreateQuizBinding
import com.example.thequiz.databinding.ActivitySaveQuestionsBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class saveQuestions : AppCompatActivity() {
    private lateinit var binding:ActivitySaveQuestionsBinding
    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding= ActivitySaveQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name=intent.getStringExtra("Name")
        val noOfQues= intent.getStringExtra("NoOfQues")
        val n:Int= noOfQues!!.toInt()
        val finalQuestion=getQuestions(n)

        ref= FirebaseDatabase.getInstance().getReference().child("Quiz").child(name!!)



    }
    fun getQuestions(n:Int):ArrayList<questions>{
        val questions=ArrayList<questions>()
        var i=1
        binding.btnSave.setOnClickListener {
            if (binding.question.text.toString().isNotEmpty() && binding.answer.text.toString().isNotEmpty()) {
                ref.child(i.toString()).setValue(saveQuestions())
                if (i == n) {
                    Log.d("question", ArrayList<questions>().toString())
                    val intent= Intent(this,MainActivity::class.java)
                    intent.putExtra("name",intent.getStringExtra("Name"))
                    Toast.makeText(
                        this,
                        "Sucessfully submited",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(intent)
                }
                i+=1
                binding.question.getText()!!.clear()
                binding.option1.getText().clear()
                binding.option2.getText().clear()
                binding.option3.getText().clear()
                binding.option4.getText().clear()
                binding.answer.getText().clear()
                if(i==n)
                    binding.btnSave.text = "Submit"


            } else
                Toast.makeText(
                    this,
                    "Please enter question and correct answer",
                    Toast.LENGTH_SHORT
                ).show()

        }

        return questions

    }
    fun saveQuestions():questions{
        val question=binding.question.text.toString()
        val option1=binding.option1.text.toString()
        val option2=binding.option2.text.toString()
        val option3=binding.option3.text.toString()
        val option4=binding.option4.text.toString()
        val crctans=binding.answer.text.toString().toInt()

        val data=questions(question,option1,option2,option3,option4,crctans)
        return data

    }
}