package com.capitals

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val countries = mapOf(
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

        println(countries.get("Австралия"))



    }

}
