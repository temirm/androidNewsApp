package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.NewsService.NewsDetailsModel

private const val ARG_NEWS_DETAILS = "news_details"

class NewsDetailsFragment : Fragment() {

    private var newsDetails: NewsDetailsModel? = null

    private lateinit var newsTitle: TextView
    private lateinit var newsAuthor: TextView
    private lateinit var newsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsDetails = it.getSerializable(ARG_NEWS_DETAILS) as NewsDetailsModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setData()
    }

    private fun initViews(view: View) {
        newsTitle = view.findViewById(R.id.tv_news_details_title)
        newsAuthor = view.findViewById(R.id.tv_news_details_author)
        newsText = view.findViewById(R.id.tv_news_details_text)
    }

    private fun setData() {
        newsDetails?.let {
            newsTitle.text = it.title
            newsAuthor.text = it.author
            newsText.text = it.text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(newsDetails: NewsDetailsModel) =
            NewsDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_NEWS_DETAILS, newsDetails)
                }
            }
    }
}