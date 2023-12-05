package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.newsapp.NewsService.NewsListModel

class MainActivity : AppCompatActivity(), NewsClickListener {

    private val service = NewsService()
    private var newsList: ArrayList<NewsListModel> = arrayListOf()
    private var currentNewsId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNewsListFragment()
        setButtonClickListeners()

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                currentNewsId = null
            }
        }
    }

    private fun initNewsListFragment() {
        newsList = service.getNewsList()
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_news_list,
                NewsListFragment.newInstance(newsList))
            .commit()
    }

    private fun setButtonClickListeners() {
        findViewById<ImageButton>(R.id.button_prev).setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        findViewById<ImageButton>(R.id.button_next).setOnClickListener {
            if (currentNewsId != null) {
                val currentNewsIndex = newsList.indexOfFirst { x -> x.id == currentNewsId }
                val nextNewsIndex =
                    if (currentNewsIndex == newsList.lastIndex) 0 else currentNewsIndex + 1
                val nextNewsId = newsList.elementAt(nextNewsIndex).id
                openNews(nextNewsId)
            }
        }
    }

    override fun onNewsClick(id: Int) {
        if (currentNewsId != id) {
            openNews(id)
        }
    }

    private fun openNews(id: Int) {
        // here a cache can be used instead of a service call
        val newsDetails = service.getNewsDetailsById(id) ?: return

        currentNewsId = id

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