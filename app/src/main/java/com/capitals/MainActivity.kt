package com.capitals

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val countryFromCountryCity = ArrayList(Data.countryCity.keys)
    val cityFromCountryCity = ArrayList(Data.countryCity.values)

    var randomCountry = countryFromCountryCity.get(Random.nextInt(countryFromCountryCity.size))

    fun changeRandomCapital() {
        randomCountry = countryFromCountryCity.get(Random.nextInt(countryFromCountryCity.size))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fillTextViews()

        val listOfButtons = arrayListOf<Button>(answerOne, answerTwo, answerThree, answerFour)
        for (i in listOfButtons) {
            i.setOnClickListener {
                buttonClick(it)
            }
        }

        showResult.setOnClickListener {
            goToResultActivity()
        }

        Data.questionCount = 1
        Data.countAswerGood = 0
        Data.countAnswerNot = 0
    }

    fun fillTextViews() {

        qestionTextView.text = "Столица страны \"${randomCountry}\": "

        val listOfButtons = arrayListOf<Button>(answerOne, answerTwo, answerThree, answerFour)
        Collections.shuffle(listOfButtons)

        val random = Random.nextInt(listOfButtons.size)
        val randomCapital = Data.countryCity.get(randomCountry)

        val tempArrList = mutableListOf<String>()
        tempArrList.addAll(cityFromCountryCity)
        tempArrList.remove(randomCapital)

        for (i in 0..listOfButtons.size - 1) {
            val tempTextOnButton = tempArrList.get(Random.nextInt(tempArrList.size))
            listOfButtons.get(i).text = tempTextOnButton
            listOfButtons.get(i).setBackgroundResource(R.color.cityColor)
            tempArrList.remove(tempTextOnButton)
        }

        listOfButtons.get(random).text = randomCapital

        countryFromCountryCity.remove(randomCountry)



    }

    fun buttonClick(v: View) {

        if ((v as TextView).text.equals(Data.countryCity.get(randomCountry))) {
            Data.countAswerGood++
            val toast = Toast.makeText(this, "Правильно", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0,100)
            toast.show()

        } else {
            Data.countAnswerNot++
            val toast = Toast.makeText(this, "Нет", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0,100)
            toast.show()
        }

        Log.e("AAAAAA правильный", Data.countAswerGood.toString())
        Log.e("AAAAAA не правильный", Data.countAnswerNot.toString())
        Log.e("AAA btnClick наж столиц", v.text.toString())
        Log.e("AAA столица этой страны", Data.countryCity.get(randomCountry))

        Data.questionCount++

        if (Data.questionCount == Data.countryCity.size - 1) {
            goToResultActivity()
        }

        changeRandomCapital()

        fillTextViews()
    }

    fun goToResultActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
}
