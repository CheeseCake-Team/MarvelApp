package com.abaferastech.marvelapp.ui.search

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class SearchQueryAdapter(oldQueryListener: OldQueryListener):
    BaseAdapter<SearchQueryEntity>(oldQueryListener) {
    override val layoutId: Int
        get() = R.layout.item_old_search_query
}

interface OldQueryListener:BaseInteractionListener {
    fun onSearchQueryClick(oldQueryEntity: SearchQueryEntity)

    fun onDeleteClick(oldQueryEntity: SearchQueryEntity)
}