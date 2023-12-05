package com.example.newsapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.NewsService.NewsListModel

private const val ARG_NEWS_LIST = "news_list"

class NewsListFragment : Fragment() {

    private var newsClickListener: NewsClickListener? = null
    private var newsListData: ArrayList<NewsListModel> = arrayListOf()
    private var newsTitlesViews: MutableList<TextView> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewsClickListener) {
            newsClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        newsClickListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsListData = it.getSerializable(ARG_NEWS_LIST) as ArrayList<NewsListModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsTitlesViews(view)
        setNewsTitlesText()
        setClickListeners()
    }

    private fun initNewsTitlesViews(view: View) {
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_one))
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_two))
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_three))
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_four))
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_five))
        newsTitlesViews.add(view.findViewById(R.id.tv_news_list_title_six))
    }

    private fun setNewsTitlesText() {
        newsTitlesViews.forEachIndexed { index, textView ->
            textView.text = newsListData[index].title
        }
    }

    private fun setClickListeners() {
        newsTitlesViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                newsClickListener?.onNewsClick(newsListData[index].id)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(newsList: ArrayList<NewsListModel>) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_NEWS_LIST, newsList)
                }
            }
    }
}