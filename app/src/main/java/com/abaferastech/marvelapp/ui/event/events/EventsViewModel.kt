package com.abaferastech.marvelapp.ui.event.events

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.mapper.dtotodomain.EventMapper
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: MarvelRepository,
                                          savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), EventsInteractionListener {

    val navigationEvents = MutableLiveData<Event<EvenEvents>>()

    private val _events = MutableLiveData<List<com.abaferastech.marvelapp.data.domain.models.Event>>()
    val events: LiveData<List<com.abaferastech.marvelapp.data.domain.models.Event>> get() = _events
    override val key: String
        get() = "eventsId"
    private fun convertDtoToListDomain(list: List<EventDTO>): MutableList<com.abaferastech.marvelapp.data.domain.models.Event> {
        val result = mutableListOf<com.abaferastech.marvelapp.data.domain.models.Event>()
        list.forEach {
            result.add(EventMapper().map(it))
        }
        return result
    }

    fun getMarvelEvents() {
        fetchItemsList {
            repository.getAllEvents()
        }
    }

    fun getCharacterEvents() {
        fetchItemsList {
            repository.getCharacterEvents(getPassedId()!!)
        }
    }

    fun getCreatorEvents() {

        fetchItemsList {
            repository.getCreatorEvents(getPassedId()!!)
        }
    }

    fun getComicEvents() {

        fetchItemsList {
            repository.getComicEvents(getPassedId()!!)
        }
    }

    override fun onEventClick(event: EventDTO) {
        navigationEvents.postValue(Event(EvenEvents.ClickEventEvents(event.id!!)))
    }
    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }
    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

    private fun fetchItemsList(getItemsList: () -> Single<UIState<List<EventDTO>>>) {
        getItemsList()
            .applySchedulersAndPostUIStates { dtoList ->
                _events.postValue(convertDtoToListDomain(dtoList.toData()!!))
            }
    }
}