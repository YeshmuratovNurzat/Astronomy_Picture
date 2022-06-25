package com.etoolkit.home.presentions.screen.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.domian.repository.AstronomyPictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var astronomyPictureRepository: AstronomyPictureRepository) : ViewModel(){

    private val getAllAstronomyPictureLiveData = MutableLiveData<List<AstronomyPicture>>()
    private val getDataFromApiDate = MutableLiveData<List<AstronomyPicture>>()

    fun getAllAstronomyPicture() : LiveData<List<AstronomyPicture>> {
        viewModelScope.launch {
            getAllAstronomyPictureLiveData.value = astronomyPictureRepository.getAllAstronomyPictureList()
        }
        return getAllAstronomyPictureLiveData
    }

    fun getAstronomyPictureListFromDate(startDate : String,endDate : String) : LiveData<List<AstronomyPicture>> {
        viewModelScope.launch {
            getDataFromApiDate.value = astronomyPictureRepository.getAstronomyPictureListFromDate(startDate, endDate)
        }
        return getDataFromApiDate
    }
}