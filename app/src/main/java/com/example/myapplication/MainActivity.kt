package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    var e = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button2)
        val text = findViewById<TextView>(R.id.text)

        var list_words = arrayListOf<String>()
        val arr = Array(10,{0})

        val jsonString = assets.open("words_dictionary.json").reader().readText()
        val jsonArray = JSONObject(jsonString)

        val random = Random()

        val iterator = jsonArray.keys()

        for(i in 0 until 10){
            val a = random.nextInt(370101)+1
            arr[i] = a
        }

        var c = 0
        var d = 0

        arr.sort()

        while(iterator.hasNext())
        {

            if(arr[c] == d){
                val b = iterator.next()
                list_words.add(b)
                c += 1
            }
            else{
                val a = iterator.next()
            }

            if(c == 10){
                break
            }
            d++
        }

        btn.setOnClickListener {

            CoroutineScope(Dispatchers.IO).async {
                for (i in 1..5)
                {
                    Log.d("TAG", "$i")
                }
            }

        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager2)

        var models: MutableList<String> = mutableListOf()

        for(i in 0 until list_words.size){
            val b = list_words.get(i)
            models.add(""+b)
        }

        var adapter = Adapter(models, this)
        viewPager.adapter = adapter
        viewPager.setPadding(30, 0, 30, 0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                e = position
                Log.d("TAG", "" + models.get(position))
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }

    private suspend fun setNetWork(){

    }

}