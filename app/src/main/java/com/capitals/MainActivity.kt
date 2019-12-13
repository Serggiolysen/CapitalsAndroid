package com.capitals

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //for fast coding - just 2 languages
    private val countryFromCountryCity =
        if (Locale.getDefault() == Locale.UK) ArrayList(Data.countryCityEn.keys) else ArrayList(Data.countryCityRu.keys)

    private val cityFromCountryCity =
        if (Locale.getDefault() == Locale.UK) ArrayList(Data.countryCityEn.values) else ArrayList(Data.countryCityRu.values)

    private val countryAndCityMap =
        if (Locale.getDefault() == Locale.UK) Data.countryCityEn else Data.countryCityRu

    private var randomCountry = countryFromCountryCity.get(Random.nextInt(countryFromCountryCity.size))
    private val TIME_DELAY = 2000
    private var back_pressed: Long = 0

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

        showResultButton.setOnClickListener {
            goToResultActivity()
        }

    }

    fun fillTextViews() {

        questionTextView.text = "${resources.getString(R.string.question_beginning)} \"${randomCountry}\": "

        val listOfButtons =
            arrayListOf<Button>(answerOne, answerTwo, answerThree, answerFour)
        Collections.shuffle(listOfButtons)

        val random = Random.nextInt(listOfButtons.size)
        val randomCapital = countryAndCityMap.get(randomCountry)

        val tempArrList = mutableListOf<String>()
        tempArrList.addAll(cityFromCountryCity)
        tempArrList.remove(randomCapital)

        for (i in 0..listOfButtons.size - 1) {
            val tempTextOnButton = tempArrList.get(Random.nextInt(tempArrList.size))
            listOfButtons.get(i).text = tempTextOnButton
            listOfButtons.get(i).setBackgroundResource(R.drawable.selector)
            tempArrList.remove(tempTextOnButton)
        }

        listOfButtons.get(random).text = randomCapital

        countryFromCountryCity.remove(randomCountry)

    }

    fun buttonClick(v: View) {

        if ((v as TextView).text.equals(countryAndCityMap.get(randomCountry))) {
            startGreen()
            Data.countAswerGood++
        } else {
            startRed()
            Data.countAnswerNot++
        }

        Data.questionCount++

        if (Data.questionCount == countryAndCityMap.size - 1) {
            goToResultActivity()
        }

        changeRandomCapital()

        fillTextViews()
    }

    fun changeRandomCapital() {
        if (countryFromCountryCity.size > 2) {
            randomCountry = countryFromCountryCity.get(Random.nextInt(countryFromCountryCity.size))
        } else Toast.makeText(
            this, resources.getString(R.string.no_more_countries),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun goToResultActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        Data.questionCount = 1
        Data.countAswerGood = 0
        Data.countAnswerNot = 0
        super.onDestroy()
    }

    fun startGreen() {
        val colorFrom = resources.getColor(R.color.lightGreen)
        val colorTo = resources.getColor(R.color.questionColor)
        val duration = 600L
        ObjectAnimator
            .ofObject(questionTextView, "backgroundColor", ArgbEvaluator(), colorFrom, colorTo)
            .setDuration(duration)
            .start()
    }


    fun startRed() {
        val colorFrom = resources.getColor(R.color.ligthRed)
        val colorTo = resources.getColor(R.color.questionColor)
        val duration = 600L
        ObjectAnimator
            .ofObject(questionTextView, "backgroundColor", ArgbEvaluator(), colorFrom, colorTo)
            .setDuration(duration)
            .start()
    }

    override fun onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(
                this, resources.getString(R.string.press_back_to_exit),
                Toast.LENGTH_SHORT
            ).show()
        }
        back_pressed = System.currentTimeMillis()
    }

}
