package com.abaferastech.marvelapp.ui.model

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<EventHandler<T>> {
    override fun onChanged(value: EventHandler<T>) {
        value.getContentIfNotHandled()?.let { it ->
            onEventUnhandledContent(it)
        }
    }
}