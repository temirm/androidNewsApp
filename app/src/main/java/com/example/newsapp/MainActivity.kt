package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.newsapp.NewsService.NewsDetailsModel

class MainActivity : AppCompatActivity(), NewsClickListener {

    private val service = NewsService()
    private var currentNewsId: Int? = null

    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initNewsListFragment()

        prevButton.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                currentNewsId = null
            }
        }
    }

    private fun initViews() {
        prevButton = findViewById(R.id.button_prev)
        nextButton = findViewById(R.id.button_next)
    }

    private fun initNewsListFragment() {
        val newsList = service.getNewsList()
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_news_list,
                NewsListFragment.newInstance(newsList))
            .commit()
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