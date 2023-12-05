package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.NewsService.NewsDetailsModel

class MainActivity : AppCompatActivity(), NewsClickListener {

    private val service = NewsService()
    private var currentNewsId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsList = service.getNewsList()
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_news_list,
                NewsListFragment.newInstance(newsList))
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                currentNewsId = null
            }
        }
    }

    override fun onNewsClick(id: Int) {
        if (currentNewsId != id) {
            // here a cache can be used instead of a service call
            val newsDetails = service.getNewsDetailsById(id) ?: return
            openNews(newsDetails)
            currentNewsId = id
        }
    }

    private fun openNews(newsDetails: NewsDetailsModel) {
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_news_details,
                NewsDetailsFragment.newInstance(newsDetails)
            )
            .addToBackStack(null)
            .commit()
    }
}