package com.etoolkit.home.presentions.screen.favoriteScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.etoolkit.home.databinding.FragmentFavoriteBinding
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.presentions.adapter.AstronomyPictureAdapter
import com.etoolkit.home.presentions.screen.detailtScreen.DetailActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var astronomyPictureAdapter : AstronomyPictureAdapter

    private val viewModel : FavoriteViewModel by activityViewModels<FavoriteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        init()
    }


    private fun initRecycler() {
        binding.recyclerView.apply {
            val decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            astronomyPictureAdapter = AstronomyPictureAdapter()
            adapter = astronomyPictureAdapter
            astronomyPictureAdapter.setOnClick(object : AstronomyPictureAdapter.SetClickListener{
                override fun onClick(astronomyPicture: AstronomyPicture){
                    val intent = Intent(context.applicationContext, DetailActivity::class.java)
                    intent.putExtra("astronomyPicture",astronomyPicture)
                    startActivity(intent)
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
            viewModel.data().observe(viewLifecycleOwner, {
                if (it == null) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    astronomyPictureAdapter.setListData(it.asReversed())
                    astronomyPictureAdapter.notifyDataSetChanged()
                }
            })
    }
}