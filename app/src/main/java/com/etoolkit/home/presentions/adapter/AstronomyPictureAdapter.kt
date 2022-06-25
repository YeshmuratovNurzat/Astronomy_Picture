package com.etoolkit.home.presentions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.home.R
import com.etoolkit.home.domian.model.AstronomyPicture
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class AstronomyPictureAdapter : RecyclerView.Adapter<AstronomyPictureAdapter.AstroPictureViewHolder>(){

    private var listDataAstronomyPicture = emptyList<AstronomyPicture>()
    private lateinit var clickListener : SetClickListener


    class  AstroPictureViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val imageUrl = view.findViewById<ImageView>(R.id.image_url)
        private val tvDate = view.findViewById<TextView>(R.id.tvDate)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

        fun bind(astronomyPicture: AstronomyPicture) {

//            val translationConfigs = TranslatorOptions.Builder()
//                .setSourceLanguage(TranslateLanguage.ENGLISH)
//                .setTargetLanguage(TranslateLanguage.RUSSIAN)
//                .build()
//
//            val translator = Translation.getClient(translationConfigs)
//
//            if (astronomyPicture.explanation?.isNotEmpty() == true) {
//                translator.downloadModelIfNeeded().addOnSuccessListener {}
//                    .addOnFailureListener {}
//            }
//
//            translator.translate(astronomyPicture.explanation.toString()).addOnSuccessListener {
//                astronomyPicture.explanation = it.toString()
//            }
//                .addOnFailureListener {
//                    it.printStackTrace()
//                }
//            translator.translate(astronomyPicture.title.toString()).addOnSuccessListener {
//                astronomyPicture.title = it
//            }
//                .addOnFailureListener {
//                    it.printStackTrace()
//                }

            tvDate.text = astronomyPicture.date
            tvTitle.text = astronomyPicture.title
            Glide.with(this.itemView)
                .load(astronomyPicture.hdurl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.img)
                .into(imageUrl)


            if (astronomyPicture.media_type == "video") {
                imageUrl.setImageResource(R.drawable.video)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroPictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return AstroPictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: AstroPictureViewHolder, position: Int) {

        holder.bind(listDataAstronomyPicture[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(listDataAstronomyPicture[position])
        }
    }

    override fun getItemCount(): Int {
        return listDataAstronomyPicture.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<AstronomyPicture>) {
        this.listDataAstronomyPicture = listData
        notifyDataSetChanged()
    }

    interface SetClickListener {
        fun onClick(astronomyPicture: AstronomyPicture)
    }

    fun setOnClick(setClickListener : SetClickListener){
        clickListener = setClickListener
    }

}