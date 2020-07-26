package com.wolanski.michal.healthilytest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wolanski.michal.healthilytest.entities.Venue
import com.wolanski.michal.healthilytest.network.FoursquareApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _venues = MutableLiveData<List<Venue>>()
    val venues: LiveData<List<Venue>>
        get() = _venues

    val error = MutableLiveData<Unit>()

    fun getVenues(name: String) {
        FoursquareApi.retrofitService.getVenues(name)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { responseBody ->
                    _venues.postValue(responseBody.response.venues)
                }, {
                    error.postValue(Unit)
                }
            )
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()

        super.onCleared()
    }
}