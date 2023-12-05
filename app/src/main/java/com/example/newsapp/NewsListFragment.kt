package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_NEWS_LIST = "news_list"

class NewsListFragment : Fragment() {

    private var newsList: ArrayList<NewsDetailsModel> = arrayListOf()

    private var newsTitlesViews: MutableList<TextView> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsList = it.getSerializable(ARG_NEWS_LIST) as ArrayList<NewsDetailsModel>
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
        newsTitlesViews.forEachIndexed { index, textView -> textView.text = newsList[index].title }
    }

    private fun setClickListeners() {
        newsTitlesViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.fragment_news_details,
                        NewsDetailsFragment.newInstance(newsList[index]))
                    .commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(newsList: ArrayList<NewsDetailsModel>) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_NEWS_LIST, newsList)
                }
            }
    }
}