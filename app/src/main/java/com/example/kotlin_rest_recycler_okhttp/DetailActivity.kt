package com.example.kotlin_rest_recycler_okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle : Bundle? = intent.extras;

        val creatorName : String = bundle?.get("EXTRA_CreatorName") as String
        val imageURL : String = bundle?.get("EXTRA_ImageURL") as String
        val likesCount :  Int = bundle?.get("EXTRA_LikesCount")as Int

        detail_creatorNameUI.text = creatorName
        detail_likesCountUI.text = likesCount.toString()

        val requestOptions = RequestOptions()

        Glide.with(applicationContext).setDefaultRequestOptions(requestOptions).load(imageURL).into(detail_imageViewUI)

    }
}
