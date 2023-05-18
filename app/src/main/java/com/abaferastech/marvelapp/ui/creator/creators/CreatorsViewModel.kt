package com.abaferastech.marvelapp.ui.creator.creators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.mapper.dtotodomain.CreatorMapper
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatorsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), CreatorsInteractionListener {

    private val _creators = MutableLiveData<List<Creator>>()
    val creators: LiveData<List<Creator>> get() = _creators

    val navigationEvents = MutableLiveData<Event<CreatorEvent>>()


    init {
        getMarvelCreators()
    }

    fun saveCreatorsId(passedId: Int){
        setSavedStateValue("creatorsID",passedId)
    }

    private fun getCreatorsPassedId() = getSavedStateValue<Int>("creatorsID")

    fun getMarvelCreators() {
        repository.getAllCreators()
            .applySchedulersAndPostUIStates {
                _creators.postValue(
                    convertDtoToListDomain(it.toData()!!)
                )
            }
    }

    private fun convertDtoToListDomain(list: List<CreatorDTO>): MutableList<Creator>{
        val result = mutableListOf<Creator>()
        list.forEach {
            result.add(CreatorMapper().map(it))
        }
        return result
    }

    fun getSeriesCreators() {
        getCreatorsPassedId()?.let { id ->
            repository.getSeriesCreators(id)
                .applySchedulersAndPostUIStates { dtoList ->
                    _creators.postValue(
                        convertDtoToListDomain(dtoList.toData()!!)
                    )
                }
        }
    }

    fun getComicCreators() {
        getCreatorsPassedId()?.let{ id ->
            repository.getComicCreators(id)
                .applySchedulersAndPostUIStates { dtoList ->
                    _creators.postValue(
                        convertDtoToListDomain(dtoList.toData()!!)
                    )
                }
        }
    }


    override fun onClickCreators(creators: Creator) {
        navigationEvents.postValue(Event(CreatorEvent.ClickCreatorEvent(creators.id!!)))
    }

}