package com.roysters.newyorktimes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.roysters.newyorktimes.utilis.Constant
import com.roysters.newyorktimes.utilis.showAlert

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class ArticlesActivity : AppCompatActivity() {

    private lateinit var articlesViewModel: ArticlesViewModel
    private var listingType = ArticlesListingType.LAST_DAY
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel::class.java)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setUpRecyclerView()

        refreshLayout.setOnRefreshListener {
            getArticlesData()
        }

        getArticlesData()
    }

    private fun setUpRecyclerView() {
        articlesAdapter = ArticlesAdapter {
            val intent = Intent(this, ArticlePreviewActivity::class.java)
            intent.putExtra(Constant.TITLE, it.title)
            intent.putExtra(Constant.URL, it.url)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = articlesAdapter
    }

    private fun getArticlesData() {
        refreshLayout.isRefreshing = true
        recyclerView.visibility = View.GONE
        articlesViewModel.getArticles(listingType).observe(this,
            Observer {
                refreshLayout.isRefreshing = false
                it?.let { response ->
                    if (!response.errorMessage.isNullOrEmpty()) {
                        showAlert(
                            this@ArticlesActivity,
                            getString(R.string.error_message),
                            getString(R.string.ok_title)
                        )
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        articlesAdapter.addData(response.results as ArrayList<ResultsItem>)
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_last_day -> {
                listingType = ArticlesListingType.LAST_DAY
                getArticlesData()
                true
            }
            R.id.action_last_week -> {
                listingType = ArticlesListingType.LAST_WEEK
                getArticlesData()
                true
            }
            R.id.action_last_month -> {
                listingType = ArticlesListingType.LAST_MONTH
                getArticlesData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
