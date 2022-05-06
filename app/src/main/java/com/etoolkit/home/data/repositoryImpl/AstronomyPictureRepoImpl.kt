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

    override suspend fun getAllPicture(): List<AstronomyPicture> {
        return astronomyPictureService.getDataFromApi()
    }

    override fun getFavoritePicture(): LiveData<List<AstronomyPicture>> {
        return astronomyPictureDao.getAllAstronomyPicture()
    }

    override suspend fun insertPicture(astronomyPicture: AstronomyPicture) {
        astronomyPictureDao.insertAstronomyPicture(astronomyPicture)
    }

    override suspend fun delete(astronomyPicture: AstronomyPicture) {
        astronomyPictureDao.deleteAllPicture(astronomyPicture)
    }

    override suspend fun getDataFromApiDate(start: String, end: String) : List<AstronomyPicture> {
        return astronomyPictureService.getDataFromDate(start,end)
    }
}