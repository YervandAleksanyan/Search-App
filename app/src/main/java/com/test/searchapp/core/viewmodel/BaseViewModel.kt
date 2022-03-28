package com.test.searchapp.core.viewmodel

import androidx.lifecycle.*
import com.test.searchapp.core.extensions.asLiveData
import com.test.searchapp.core.extensions.d
import com.test.searchapp.core.extensions.isActive
import com.test.searchapp.core.model.ViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

abstract class BaseViewModel<S> : ViewModel(), DefaultLifecycleObserver {

    private val viewStateMutableLiveData = MutableLiveData<ViewState<S>>(ViewState.Loading)
    val viewStateLiveData = viewStateMutableLiveData.asLiveData()

    val viewState
        get() = viewStateLiveData.value

    protected var loadDataJob: Job? = null

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        initiateDataLoad()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        initiateDataLoad()
    }

    private fun initiateDataLoad() {
        if (loadDataJob.isActive()) {
            d { "initiateDataLoad -> already in progress" }
            return
        }
        loadDataJob = viewModelScope.launch {
            if (viewState?.isSuccess() == false) {
                postViewState(ViewState.Loading)
            }
            d { "initiateDataLoad -> loadData() called" }
            loadData()
        }
    }

    protected abstract suspend fun loadData()


    fun postViewState(newViewState: ViewState<S>) {
        // Check State
        val prevViewState = viewState
        if (prevViewState != null && prevViewState.isSuccess() && newViewState.isSuccess()) {
            // Logic for showing offline notification
            d { "postViewState -> Data Updated" }
        }
        //
        viewStateMutableLiveData.value = newViewState
    }

    fun forceRestartDataLoad() {
        val prevLoadDataJob = loadDataJob
        loadDataJob = viewModelScope.launch {
            postViewState(ViewState.Loading)
            if (prevLoadDataJob.isActive()) {
                prevLoadDataJob?.cancelAndJoin()
            }
            d { "forceRestartDataLoad -> loadData() called" }
            loadData()
        }
    }

}