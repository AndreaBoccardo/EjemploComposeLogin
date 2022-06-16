package cl.mobdev.ejemplocomposelogin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Math.random

class MainViewModel: ViewModel() {

    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)

    fun isLoading(): LiveData<Boolean> = isLoading
    fun hasErrors(): LiveData<Boolean> = hasErrors

    fun loginWithGoogle(){
        isLoading.postValue(true)
        viewModelScope.launch {
            delay(5000)

            isLoading.postValue(false)

            if (random() < 0.4){
                hasErrors.postValue(true)
            }
        }
    }

    fun clearErrors() {
        hasErrors.postValue(false)
    }

}