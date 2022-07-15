package br.com.cwi.tcc_android.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cwi.tcc_android.domain.entity.Breed
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.security.auth.callback.Callback

abstract class BaseViewModel: ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    var page: Int = 0

    fun launch(block: suspend () -> Unit) {
        _loading.postValue(true)

        viewModelScope.launch {
            try {
                block()
                _error.postValue(false)
            } catch (ex: Exception) {
                _error.postValue(true)
            }
            _loading.postValue(false)
        }
    }

    fun nextPage() {
        page += 1
    }

    fun previousPage() {
        if (page != 0) page -= 1
    }
}