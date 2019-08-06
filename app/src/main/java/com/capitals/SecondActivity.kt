package com.capitals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewTrue.text = Data.countAswerGood.toString()
        textViewFalse.text = Data.countAnswerNot.toString()

        Data.questionCount = 1
        Data.countAswerGood = 0
        Data.countAnswerNot = 0
    }
}