package app.prgghale.cleanarchitecture.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.prgghale.cleanarchitecture.network.repository.MainRepository
import app.prgghale.cleanarchitecture.domain.SummaryResponse
import app.prgghale.cleanarchitecture.extensions.MainViewState
import app.prgghale.cleanarchitecture.utils.ResourceStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepository: MainRepository
) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data
    fun addData(value: String) {
        viewModelScope.launch {
            _data.value = testRepository.getUserName()
        }
    }

    /**
     * Get summary data from server*/
    private val _summary = MutableStateFlow<ResourceStates<SummaryResponse>>(ResourceStates.None())
    val summary: StateFlow<ResourceStates<SummaryResponse>> = _summary
    fun getSummary() {
        viewModelScope.launch {
            _summary.value = ResourceStates.Loading()
            _summary.value = testRepository.getSummary()
        }
    }
}