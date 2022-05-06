package com.etoolkit.home.di

import android.content.Context
import androidx.room.Room
import com.etoolkit.home.data.repositoryImpl.AstronomyPictureRepoImpl
import com.etoolkit.home.data.retrofit.AstronomyPictureService
import com.etoolkit.home.data.room.AstronomyPictureDao
import com.etoolkit.home.data.room.AstronomyPictureDataBase
import com.etoolkit.home.domian.repository.AstronomyPictureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AstronomyPictureDataBase {
        return Room.databaseBuilder(
            context,
            AstronomyPictureDataBase::class.java,
            "astronomyPictureDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getAppDao(appDatabase: AstronomyPictureDataBase): AstronomyPictureDao {
        return appDatabase.getAstronomyPictureDao()
    }

    val BASE_URL = "https://api.nasa.gov/planetary/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): AstronomyPictureService {
        return retrofit.create(AstronomyPictureService::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun repository(astronomyPictureRepoImpl: AstronomyPictureRepoImpl) : AstronomyPictureRepository{
        return astronomyPictureRepoImpl
    }

}