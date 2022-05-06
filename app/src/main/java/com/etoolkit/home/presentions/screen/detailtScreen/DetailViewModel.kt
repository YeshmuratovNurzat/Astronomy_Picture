package com.etoolkit.home.presentions.screen.detailtScreen

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
class DetailViewModel @Inject constructor(var astronomyPictureRepository: AstronomyPictureRepository) : ViewModel(){

    val detailData = MutableLiveData<AstronomyPicture>()

    fun insertPicture(astronomyPicture: AstronomyPicture){
        viewModelScope.launch(Dispatchers.IO) {
            astronomyPictureRepository.insertPicture(astronomyPicture)
        }
    }

    fun deletePicture(astronomyPicture: AstronomyPicture){
        viewModelScope.launch(Dispatchers.IO) {
            astronomyPictureRepository.delete(astronomyPicture)
        }
    }

}