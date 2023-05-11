package com.abaferastech.marvelapp.ui.model

open class Event<out T>(private val content: T) {
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

class NavigationEvent(private val destinationTYPE: TYPE) : Event<TYPE>(destinationTYPE)

