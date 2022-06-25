package com.etoolkit.home.data.repositoryImpl

import androidx.lifecycle.LiveData
import com.etoolkit.home.data.retrofit.AstronomyPictureService
import com.etoolkit.home.data.room.AstronomyPictureDao
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.domian.repository.AstronomyPictureRepository
import javax.inject.Inject

class AstronomyPictureRepoImpl @Inject constructor(private var astronomyPictureDao: AstronomyPictureDao,
                                                   private var astronomyPictureService: AstronomyPictureService)
    : AstronomyPictureRepository{

    override suspend fun getAllAstronomyPictureList(): List<AstronomyPicture> {
        return astronomyPictureService.getAstronomyPictureList()
    }

    override fun getAllFavoriteAstronomyPictureList(): LiveData<List<AstronomyPicture>> {
        return astronomyPictureDao.getAllAstronomyPicture()
    }

    override suspend fun insertAstronomyPicture(astronomyPicture: AstronomyPicture) {
        astronomyPictureDao.insertAstronomyPicture(astronomyPicture)
    }

    override suspend fun deleteAstronomyPicture(astronomyPicture: AstronomyPicture) {
        astronomyPictureDao.deleteAstronomyPicture(astronomyPicture)
    }

    override suspend fun getAstronomyPictureListFromDate(startDate : String, endDate : String) : List<AstronomyPicture> {
        return astronomyPictureService.getAstronomyPictureListFromDate(startDate,endDate)
    }
}