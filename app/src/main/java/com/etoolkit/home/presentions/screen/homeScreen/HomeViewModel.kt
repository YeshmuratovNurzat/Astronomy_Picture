package com.etoolkit.home.presentions.screen.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.domian.repository.AstronomyPictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var astronomyPictureRepository: AstronomyPictureRepository) : ViewModel(){

    val getAllAstronomyPictureLiveData = MutableLiveData<List<AstronomyPicture>>()
    val getDate = MutableLiveData<List<AstronomyPicture>>()

    fun getAllData() : LiveData<List<AstronomyPicture>> {
        viewModelScope.launch(Dispatchers.Main) {
            getAllAstronomyPictureLiveData.value = astronomyPictureRepository.getAllPicture()
        }
        return getAllAstronomyPictureLiveData
    }

    fun getDataFromDateApi(start : String,end : String) : LiveData<List<AstronomyPicture>> {
        viewModelScope.launch(Dispatchers.Main) {
            getDate.value = astronomyPictureRepository.getDataFromApiDate(start, end)
        }
        return getDate
    }
}