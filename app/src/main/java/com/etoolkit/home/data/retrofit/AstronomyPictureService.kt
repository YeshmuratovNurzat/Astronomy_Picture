package com.etoolkit.home.data.retrofit

import com.etoolkit.home.domian.model.AstronomyPicture
import retrofit2.http.GET
import retrofit2.http.Query


interface AstronomyPictureService {

    @GET("apod?start_date=2022-03-10&end_date&api_key=o9BezztHg3bsI3DAK0wyYlI3lIVsWwhmzFqUoK7z")
    suspend fun getAstronomyPictureList() : List<AstronomyPicture>

    @GET("apod?api_key=o9BezztHg3bsI3DAK0wyYlI3lIVsWwhmzFqUoK7z")
    suspend fun getAstronomyPictureListFromDate(@Query("start_date") start_date : String,
                                @Query("end_date") end_date : String) : List<AstronomyPicture>

}