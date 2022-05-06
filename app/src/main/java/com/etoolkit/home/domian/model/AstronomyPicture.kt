package com.etoolkit.home.domian.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "astronomy_picture")
class AstronomyPicture(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "copyright")
    val copyright: String?,

    @ColumnInfo(name = "date")
    val date: String?,

    @ColumnInfo(name = "explanation")
    var explanation: String?,

    @ColumnInfo(name = "title")
    var title : String?,

    @ColumnInfo(name = "url")
    val url : String?,

    @ColumnInfo(name = "hdurl")
    val hdurl: String?,

    @ColumnInfo(name = "media_type")
    val media_type : String?,

) : Serializable