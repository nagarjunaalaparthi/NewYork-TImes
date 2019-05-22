package com.roysters.newyorktimes.network

import okhttp3.*

class OkHttpRequest {

    private var client = OkHttpClient()

    fun get(url: String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

}