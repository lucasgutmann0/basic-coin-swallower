package com.example.tragamonedas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tragamonedas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var boxOne: ImageView
    private lateinit var boxTwo: ImageView
    private lateinit var boxThree: ImageView
    private lateinit var resultText: TextView
    private var images: List<Int> = listOf<Int>(R.drawable.coin_icon, R.drawable.star_icon, R.drawable.strawberry_icon)

    private fun getRandomImageList():List<Int>{
        val imageList = mutableListOf<Int>()
        for (i in 1..3) {
            val number = (0..2).random()
            imageList.add(images[number])
        }
        return imageList
    }

    private fun setRandomImagesIntoBoxes(): List<Int>{
        val imgList: List<Int> = getRandomImageList()
        for (i in 0..2){
            var box: ImageView = boxOne
            when (i) {
                0 -> box = boxOne
                1 -> box = boxTwo
                2 -> box = boxThree
            }
            box.setImageResource(imgList[i])
        }
        return imgList
    }

    @SuppressLint("SetTextI18n")
    private fun changeTextResult(equals: Int){
        if (equals == 3){
            resultText.text = "HAS GANADO, FELICITACIONES!!"
        } else {
            resultText.text = "Rayos!! intenta nuevamente"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setup binding
        val binding  = ActivityMainBinding.inflate(layoutInflater)

        // get elements from layout
        boxOne = binding.firstBox
        boxTwo = binding.secondBox
        boxThree = binding.thirdBox
        resultText = binding.resultTextview
        val button = binding.playBtn
        setContentView(binding.root)

        // set default images to boxes
        boxOne.setImageResource(R.drawable.star_icon)
        boxTwo.setImageResource(R.drawable.coin_icon)
        boxThree.setImageResource(R.drawable.strawberry_icon)

        // add functionality to button
        button.setOnClickListener{
            val list = setRandomImagesIntoBoxes()
            var equals: Int = 0
            equals = if (list[0] == list[1] && list[1] == list[2]){
                3
            } else {
                0
            }
            changeTextResult(equals)
        }
    }
}