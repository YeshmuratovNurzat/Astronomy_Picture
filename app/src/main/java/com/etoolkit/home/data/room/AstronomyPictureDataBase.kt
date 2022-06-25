package com.etoolkit.home.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.etoolkit.home.domian.model.AstronomyPicture

@Database(entities = [AstronomyPicture::class],version = 1)
abstract class AstronomyPictureDataBase : RoomDatabase() {

    abstract fun getAstronomyPictureDao(): AstronomyPictureDao

}