package com.etoolkit.home.presentions.screen.detailtScreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.home.databinding.FragmentDetailBinding
import com.etoolkit.home.domian.model.AstronomyPicture
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by activityViewModels<DetailViewModel>()

    private lateinit var youTubePlayerView : YouTubePlayerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


    private fun getAstronomyPictureData(astronomyPicture: AstronomyPicture) {

        binding.title.text = astronomyPicture.title.toString()
        binding.description.text = astronomyPicture.explanation.toString()
        binding.copyright.text = astronomyPicture.copyright
        binding.date.text = astronomyPicture.date
        Glide.with(this)
            .load(astronomyPicture.hdurl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.image)

        activity?.title = binding.title.text

        if (astronomyPicture.media_type == "video") {
            lifecycle.addObserver(binding.youtubePlayerView)

            binding.youtubePlayerView.visibility = View.VISIBLE
            binding.image.visibility = View.GONE

            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {

                override fun onApiChange(youTubePlayer: YouTubePlayer) {
                    super.onApiChange(youTubePlayer)

                    val s: String = astronomyPicture.url.toString()

                    youTubePlayer.cueVideo(s, 0f)
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.detailData.observe(viewLifecycleOwner,{
            getAstronomyPictureData(it)
        })
    }
}