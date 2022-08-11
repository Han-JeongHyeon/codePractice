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

//전반적으로 주석을 다는게 좋습니다.
class MainActivity : AppCompatActivity() {

    var e = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button2)

        var list_words = arrayListOf<String>()

        //COMMENT : 에셋 문자열 같은 경우는 상수로 정의해서 사용하면 향후 운영하기 더 편합니다.
        val jsonString = assets.open("words_dictionary.json").reader().readText()
        val jsonArray = JSONObject(jsonString)

        val random = Random()

        //COMMENT : 370101 의 숫자가 어디서 나왔는지 추정하기 힘드네요.
        //array의 length라던가 가변하는 데이터의 길이라면 길이를 구해서 써야 할 듯.

<<<<<<< HEAD
        //랜덤으로 json 단어를 가지고 온다
=======
        //COMMENT : 370101 의 숫자가 어디서 나왔는지 추정하기 힘드네요.
        //array의 length라던가 가변하는 데이터의 길이라면 길이를 구해서 써야 할 듯.

>>>>>>> 1edec1916b3003e08ee8ab1a58a31e9b0cd38aab
        for(i in 0 until 10){
            val RandomNum = random.nextInt(jsonArray.length())
            list_words.add(""+jsonArray.names().get(RandomNum))
        }

        // COMMENT : iterator hasNext와 next() 를 사용하는 깔끔한 코드 방식은 아닌 듯 합니다.
        // 이미 jsonArray로 가지고 있는데 이렇게 구현할 이유가 있을까요?
        // 필요한게 앞단 10개라면 훨씬 간단한 코드로 구현할 수 있을 듯.

<<<<<<< HEAD
        // COMMENT: 최초의 주어진 데이터셋에서 10개를 랜덤으로 뽑는 것이 이럻게 긴 코드일 필요가 있을까요.
        // 조금 더 고민해서 의미를 알기 쉬우면서 축약하면 좋을 듯.
=======
        arr.sort()

        // COMMENT : iterator hasNext와 next() 를 사용하는 깔끔한 코드 방식은 아닌 듯 합니다.
        // 이미 jsonArray로 가지고 있는데 이렇게 구현할 이유가 있을까요?
        // 필요한게 앞단 10개라면 훨씬 간단한 코드로 구현할 수 있을 듯.
        while(iterator.hasNext())
        {

            if(arr[c] == d){
                val b = iterator.next()
                list_words.add(b)
                c += 1
            }
            else{
                //COMMENT: 의미없는 assign 인 듯 합니다.
                val a = iterator.next()
            }

            if(c == 10){
                break
            }
            d++
        }
>>>>>>> 1edec1916b3003e08ee8ab1a58a31e9b0cd38aab

        // COMMENT: 최초의 주어진 데이터셋에서 10개를 랜덤으로 뽑는 것이 이럻게 긴 코드일 필요가 있을까요.
        // 조금 더 고민해서 의미를 알기 쉬우면서 축약하면 좋을 듯.


        val viewPager = findViewById<ViewPager2>(R.id.view_pager2)

        //COMMENT : jsonArray-> list_words -> models
        //불필요한 작업들이 많은 듯 합니다. 처음부터 models에서 사용할거면 jsonArray에서 바로 models에 넣는게 좋을 거 같네요.
<<<<<<< HEAD
=======

        var models: MutableList<String> = mutableListOf()
>>>>>>> 1edec1916b3003e08ee8ab1a58a31e9b0cd38aab

        var adapter = Adapter(list_words, this)
        viewPager.adapter = adapter
        viewPager.setPadding(30, 0, 30, 0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                e = position
                Log.d("TAG", "" + e)
            }

        })

        //COMMENT : 뷰와 컨트롤러를 따로 분리하는 이유가 있을 거 같네요.
        //꼭 코딩으로 이렇게 세팅하는거보다 뷰 파일에서 이벤트를 거는게 좋을 듯 합니다.
<<<<<<< HEAD
=======
        
        btn.setOnClickListener{
            var aa = "" //변수명도 의미가 있었으면 좋겠습니다.
>>>>>>> 1edec1916b3003e08ee8ab1a58a31e9b0cd38aab

        btn.setOnClickListener{
            var ApiMessage = "" //변수명도 의미가 있었으면 좋겠습니다.

            //Alert 팝업
            Handler().postDelayed({
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle("단어")
                    .setMessage("\n"+ApiMessage.trim())
                    .setPositiveButton("확인",null)
                    .show()

            },800)

            //api 요청
            api.getBestSeller(""+list_words.get(e)).enqueue(object: Callback<List<Responsee>>{

                //api 요청 실패 처리
                override fun onFailure(call: Call<List<Responsee>>, t: Throwable) {
                    Log.d("Error", ""+t.toString())
                }

                //api 요청 성공 처리
                override fun onResponse(call: Call<List<Responsee>>, response: Response<List<Responsee>>) {

                    //api에 단어가 없을 때
                    if(response.isSuccessful.not()){
                        ApiMessage = "등록되지 않은 단어입니다."
                        return
                    }

                    //api에 단어가 있을 때
                    response.body()?.let{

                        Log.d("TAG", "$it")

                        //COMMENT 이런 방식 말고 더 깔끔하고 범용적인 방법이 없을까요 ?
                        //parse query string

                        //특수 문자 & 필요없는 문자 없애기
                        val array = arrayOf("Responsee","meanings","Meanings","definitions","Ddefinition","definition")

                        ApiMessage = it.toString()

                        array.forEach { Delete ->
                            ApiMessage = ApiMessage.replace("$Delete"," ")
                        }
                        ApiMessage = ApiMessage.replace("[^\\w+]".toRegex()," ")
                        ApiMessage = ApiMessage.replace("\\s+".toRegex()," ")

<<<<<<< HEAD
=======
                            Log.d("TAG", responses)

                            //COMMENT 이런 방식 말고 더 깔끔하고 범용적인 방법이 없을까요 ?
                            //parse query string

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
>>>>>>> 1edec1916b3003e08ee8ab1a58a31e9b0cd38aab
                    }
                }
            })

        }

    }

}