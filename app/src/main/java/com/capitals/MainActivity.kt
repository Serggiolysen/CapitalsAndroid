package com.capitals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        var count: Int = 1
        var countAswerGood: Int = 0
        var countAnswerNot: Int = 0
    }


    val countries = mutableMapOf(
        1 to "Австралия",
        2 to "Австрия",
        3 to "Азербайджан",
        4 to "Албания",
        5 to "Алжир",
        6 to "Ангола",
        7 to "Андорра",
        8 to "Антигуа и Барбуда",
        9 to "Аргентина",
        10 to "Армения",
        11 to "Аруба",
        12 to "Афганистан"
    )

    val cities = mutableMapOf(
        1 to "Канберра",
        2 to "Вена",
        3 to "Баку",
        4 to "Тирана",
        5 to "Алжир",
        6 to "Луанда",
        7 to "Андорра - ла - Велья",
        8 to "Сент - Джонс",
        9 to "Буэнос - Айрес",
        10 to "Ереван",
        11 to "Ораньестад",
        12 to "Кабул"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewsFill()

        answerOne.setOnClickListener {
            buttonClick(it)
        }

        answerTwo.setOnClickListener {
            buttonClick(it)
        }

        answerThree.setOnClickListener {
            buttonClick(it)
        }

        answerFour.setOnClickListener {
            buttonClick(it)
        }


    }

    fun textViewsFill() {
        qestionTextView.text = "${countries.get(count)} столица: "

//        val cities2 = mutableMapOf<Int, String>()
//        cities2.putAll(cities)
//        cities2.remove(count)
//
//        val cities3 = mutableMapOf<Int, String>()
//        cities3.putAll(cities2)

        answerOne.text = cities.get(Random.nextInt(1, countries.size/3))
        answerTwo.text = cities.get(Random.nextInt(countries.size/3+1, countries.size/3*2))
        answerThree.text = cities.get(count)
        answerFour.text = cities.get(Random.nextInt((countries.size/3*2)+1, countries.size))

        Log.e("AAAAAA cities", cities.toString())
//        Log.e("AAAAAA cities2", cities2.toString())
//        Log.e("AAAAAA citiesS  size", cities2.size.toString())

        if (count == 7) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("AswerGood", countAswerGood)
            startActivity(intent)
        }
    }

    fun buttonClick(v: View) {

        if ((v as TextView).text.equals(cities.get(count))) {
            countAswerGood++
        } else
            countAnswerNot++

        Log.e("AAAAAA", count.toString())
        Log.e("AAAAAA правильный", countAswerGood.toString())
        Log.e("AAAAAA не правильный", countAnswerNot.toString())

        count++

        textViewsFill()

    }
}
