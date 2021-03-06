package com.example.android.newswithkotlin


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.newswithkotlin.database.News

class GetFavoriteNewsFromDatabaseFragment : Fragment() {

    interface FavoriteNewsFetchingRequestListener {
        fun onNewsFetchCallMade(newsList: ArrayList<News>)
    }

    var newsTableQueryListener: FavoriteNewsFetchingRequestListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search
                , container, false)
        return view
    }


    private fun setupViewModel() {
        val viewModel = ViewModelProviders.of(this).get(AllNewsViewModel::class.java)
        viewModel.newses.observe(this, object : Observer<List<News>> {
            override fun onChanged(newsEntries: List<News>?) {
                val newsArrayList = ArrayList<News>()
                for (news in newsEntries!!) {
                    newsArrayList.add(news)
                }
                newsTableQueryListener?.onNewsFetchCallMade(newsArrayList)
            }
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsTableQueryListener = activity as FavoriteNewsFetchingRequestListener
        setupViewModel()
    }
}