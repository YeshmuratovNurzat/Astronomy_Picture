package com.etoolkit.home.presentions.screen.favoriteScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.domian.repository.AstronomyPictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var astronomyPictureRepository: AstronomyPictureRepository) : ViewModel() {

    fun getAllFavoriteAstronomyPictureList() : LiveData<List<AstronomyPicture>> {
        return astronomyPictureRepository.getAllFavoriteAstronomyPictureList()
    }
}