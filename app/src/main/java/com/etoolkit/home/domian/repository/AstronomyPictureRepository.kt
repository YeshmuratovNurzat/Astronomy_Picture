package com.etoolkit.home.domian.repository


import androidx.lifecycle.LiveData
import com.etoolkit.home.domian.model.AstronomyPicture

interface AstronomyPictureRepository {

    suspend fun getAllAstronomyPictureList(): List<AstronomyPicture>

    fun getAllFavoriteAstronomyPictureList() : LiveData<List<AstronomyPicture>>

    suspend fun insertAstronomyPicture(astronomyPicture: AstronomyPicture)

    suspend fun deleteAstronomyPicture(astronomyPicture: AstronomyPicture)

    suspend fun getAstronomyPictureListFromDate(startDate : String,endDate : String) : List<AstronomyPicture>


}