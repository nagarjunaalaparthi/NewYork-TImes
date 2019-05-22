package com.roysters.newyorktimes

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.roysters.newyorktimes.network.OkHttpRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class ArticlesViewModel: ViewModel() {

    val articleData = MutableLiveData<ArticleResponse?>()

    fun getArticles(listingType: ArticlesListingType): MutableLiveData<ArticleResponse?> {
        val url = "https://api.nytimes.com/svc/mostpopular/v2/emailed/${listingType.value}.json?api-key=PPSm1diek651l32WczamwzPwixhF5ilT"
        Log.i("UrlIs", url)
        OkHttpRequest().get(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val response = ArticleResponse()
                response.errorMessage = e.message
                articleData.postValue(response)

            }

            override fun onResponse(call: Call, response: Response) {
                articleData.postValue(Gson().fromJson(response.body()?.string(), ArticleResponse::class.java))
            }

        })

        return articleData
    }
}