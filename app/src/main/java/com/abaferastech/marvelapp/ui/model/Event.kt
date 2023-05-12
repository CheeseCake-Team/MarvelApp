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
class NavigationEvent(destinationTYPE: TYPE,val destinationID:Int) : Event<TYPE>(destinationTYPE)

