package com.etoolkit.home.domian.repository


import androidx.lifecycle.LiveData
import com.etoolkit.home.domian.model.AstronomyPicture

interface AstronomyPictureRepository {

    suspend fun getAllPicture(): List<AstronomyPicture>

    fun getFavoritePicture() : LiveData<List<AstronomyPicture>>

    suspend fun insertPicture(astronomyPicture: AstronomyPicture)

    suspend fun delete(astronomyPicture: AstronomyPicture)

    suspend fun getDataFromApiDate(start : String,end : String) : List<AstronomyPicture>


}