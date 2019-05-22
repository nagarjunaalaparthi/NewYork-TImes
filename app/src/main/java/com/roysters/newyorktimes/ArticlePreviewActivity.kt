package com.roysters.newyorktimes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.roysters.newyorktimes.utilis.Constant
import kotlinx.android.synthetic.main.activity_preview.*

class ArticlePreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = intent?.extras?.getString(Constant.TITLE)

        loadWeb()

    }

    private fun loadWeb() {
        webView.settings.javaScriptEnabled = true
        webView?.apply {
            loadUrl(intent.extras.getString(Constant.URL))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}