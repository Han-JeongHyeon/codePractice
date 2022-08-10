package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.HandlerCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.RetrofitClass.api
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.*
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

        //COMMENT : 에셋 문자열 같은 경우는 상수로 정의해서 사용하면 향후 운영하기 더 편합니다.
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

        btn.setOnClickListener{
            var aa = ""

            Handler().postDelayed({
                    val builder = AlertDialog.Builder(this@MainActivity)
                        .setTitle("단어")
                        .setMessage("\n"+aa)
                        .setPositiveButton("확인",null)
                        .show()

            },800)


            api.getBestSeller(""+models.get(e))
                .enqueue(object: Callback<List<Responsee>>{
                    override fun onFailure(call: Call<List<Responsee>>, t: Throwable) {
                        //todo 실패처리

                        Log.d("Error", ""+t.toString())
                    }

                    override fun onResponse(call: Call<List<Responsee>>, response: Response<List<Responsee>>) {
                        //todo 성공처리
                        Log.d("TAG", "성공")
                        if(response.isSuccessful.not()){
                            aa = "등록되지 않은 단어입니다."
                            Log.d("TAG", "등록되지 않은 단어입니다.")
                            return
                        }
                        response.body()?.let{
                            //body가 있다면 그안에는 bestSellerDto가 들어있을것
                            val responses = response.body().toString()

                            Log.d("TAG", responses)

                            val arr = responses.split("definition=")

                            for (i in arr.indices)
                            {
                                if(i == 0) {}
                                else{
                                    val start = arr[i].indexOf("")
                                    var end = 0
                                    if(arr[i].indexOf(")]") != -1)
                                    {
                                        end = arr[i].indexOf(")]")
                                    }
                                    else if(arr[i].indexOf("),") != -1){
                                        end = arr[i].indexOf("),")
                                    }else{
                                        end = arr[i].indexOf(")")
                                    }
                                    aa = aa + arr[i].substring(start,end) + "\n"
                                    Log.d("arr", ""+arr[i].substring(start,end))
                                }
                            }

                        }
                    }

                })

            }

    }
    
}