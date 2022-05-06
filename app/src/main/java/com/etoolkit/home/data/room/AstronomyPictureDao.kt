package com.etoolkit.home.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.etoolkit.home.domian.model.AstronomyPicture

@Dao
interface AstronomyPictureDao {

    @Query("SELECT * FROM astronomy_picture ORDER BY astronomy_picture.date DESC" )
    fun getAllAstronomyPicture() : LiveData<List<AstronomyPicture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronomyPicture(astronomyPicture: AstronomyPicture)

    @Delete
    suspend fun deleteAllPicture(astronomyPicture: AstronomyPicture)

}