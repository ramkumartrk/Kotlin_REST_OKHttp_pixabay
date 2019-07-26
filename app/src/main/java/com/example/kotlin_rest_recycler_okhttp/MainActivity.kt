
package com.example.kotlin_rest_recycler_okhttp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity(),CustomAdapter.IonItemClickListener {

    val list : ArrayList<CardModel>  = ArrayList();

    override fun ItemClick(position : Int) {

       val intent  : Intent = Intent(applicationContext,DetailActivity::class.java)
        intent.putExtra("EXTRA_CreatorName",list[position].creatorName)
        intent.putExtra("EXTRA_LikesCount",list[position].likesCount)
        intent.putExtra("EXTRA_ImageURL",list[position].imageUrl)
        startActivity(intent);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewUI = findViewById(R.id.recyclerViewUI) as RecyclerView

        recyclerViewUI.layoutManager = LinearLayoutManager(this)

//        list.add(CardModel("https:google.com","Ramkumar",10))
//        list.add(CardModel("https:yahoo.com","Thilagamani",20))
//        list.add(CardModel("https:microsoft.com","kalaimani",30))
//        list.add(CardModel("https:apple.com","Santhosh",40))
//        list.add(CardModel("https:intel.com","sanjit",50))



       fetchJson();
//        val customAdapter :CustomAdapter = CustomAdapter(applicationContext,list);
//        recyclerViewUI.adapter = customAdapter
//
//
//
//        customAdapter.setOnItemClickListener(this@MainActivity);

    }

    fun fetchJson()
    {
        println("Attempting to fetch JSON");
        val url : String = "https://pixabay.com/api/?key=12805391-2a52283043ae4b47ccad55ba6&q=food";

        val activity:MainActivity = this

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client. newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {

                try{

                    val body : String? = response?.body()?.string()
                    val jsonObject : JSONObject = JSONObject(body)
                    val jsonArray : JSONArray = jsonObject.getJSONArray("hits");


                    for(i in 0..jsonArray.length()-1)
                    {
                        val hit: JSONObject = jsonArray.getJSONObject(i)

                        val createrName = hit.getString("user");
                        val likesCount = hit.getInt("likes");
                        val imageURL = hit.getString("webformatURL")
                        list.add(CardModel(imageURL,createrName,likesCount))

                    }
                    runOnUiThread(Runnable {
                        val customAdapter :CustomAdapter = CustomAdapter(applicationContext,list);
                        recyclerViewUI.adapter = customAdapter



                        customAdapter.setOnItemClickListener(activity); })


                }
                catch(e : Exception)
                {
                    println(e)
                }
                 }
            override fun onFailure(call: Call, e: IOException) {
                    println("failed to execute request");
            }
        })
    }
}
