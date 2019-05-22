package com.roysters.newyorktimes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.roysters.newyorktimes.utilis.Constant
import com.roysters.newyorktimes.utilis.isNetworkAvailable
import com.roysters.newyorktimes.utilis.showAlert
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
        if (isNetworkAvailable(this)) {
            webView?.apply {
                loadUrl(intent.extras.getString(Constant.URL))
            }

        } else {
            showAlert(
                this,
                getString(R.string.network_error_message),
                getString(R.string.ok_title)
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}