package com.abaferastech.marvelapp.ui.model

import androidx.lifecycle.Observer

open class EventModel<out T>(private val content: T) {
    private var hasBeenHandled = false
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
    fun peekContent(): T = content
}
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<EventModel<T>> {
    override fun onChanged(value: EventModel<T>) {
        value.getContentIfNotHandled()?.let { it ->
            onEventUnhandledContent(it)
        }
    }
}
