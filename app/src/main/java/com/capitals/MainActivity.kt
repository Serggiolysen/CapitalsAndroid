package com.capitals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import java.util.Map
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

    val countryCity = mutableMapOf(
        "Австралия" to "Канберра",
        "Австрия" to "Вена",
        "Азербайджан" to "Баку",
        "Албания" to "Тирана",
        "Алжир" to "Алжир",
        "Ангола" to "Луанда",
        "Андорра" to "Андорра - ла - Велья",
        "Антигуа и Барбуда" to "Сент - Джонс",
        "Аргентина" to "Буэнос - Айрес",
        "Армения" to "Ереван",
        "Аруба" to "Ораньестад",
        "Афганистан" to "Кабул"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewsFill()

        answerOne.setOnClickListener {
            buttonClick(it)
            randomizer()
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

        answerOne.text = cities.get(Random.nextInt(1, countries.size / 3))
        answerTwo.text = cities.get(Random.nextInt(countries.size / 3 + 1, countries.size / 3 * 2))
        answerThree.text = cities.get(count)
        answerFour.text = cities.get(Random.nextInt((countries.size / 3 * 2) + 1, countries.size))

        Log.e("AAAAAA cities", cities.toString())

        if (count == 7) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("AswerGood", countAswerGood)
            startActivity(intent)
        }
    }

    fun randomizer() {

        for (entry in countryCity) {
            println("${entry.key} - ${entry.value}")
        }
//        for (i in countries){
//            if ()
//            Random.nextInt(1, countries.size)

//            println(i.toString())
//    }
    }

    fun buttonClick(v: View) {

        if ((v as TextView).text.equals(cities.get(count))) {
            countAswerGood++
        } else
            countAnswerNot++

//        Log.e("AAAAAA", count.toString())
//        Log.e("AAAAAA правильный", countAswerGood.toString())
//        Log.e("AAAAAA не правильный", countAnswerNot.toString())

        count++

        textViewsFill()

    }
}
