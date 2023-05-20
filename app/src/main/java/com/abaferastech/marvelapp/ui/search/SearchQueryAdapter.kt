package com.abaferastech.marvelapp.ui.search

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.SearchQuery
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class SearchQueryAdapter(oldQueryListener: OldQueryListener):
    BaseAdapter<SearchQuery>(oldQueryListener) {
    override val layoutId: Int
        get() = R.layout.item_old_search_query
}

interface OldQueryListener:BaseInteractionListener {
    fun onSearchQueryClick(oldQueryEntity: SearchQuery)

    fun onDeleteClick(oldQueryEntity: SearchQuery)
}