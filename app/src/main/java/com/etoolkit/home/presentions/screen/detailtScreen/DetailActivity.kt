package com.etoolkit.home.presentions.screen.detailtScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.etoolkit.home.R
import com.etoolkit.home.domian.model.AstronomyPicture
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()
    private lateinit var astronomyPicture : AstronomyPicture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,DetailFragment()).commit()

        val bundle : Bundle ?= intent.extras

        astronomyPicture = bundle!!.getSerializable("astronomyPicture") as AstronomyPicture

        viewModel.detailAstronomyPictureLiveData.value = astronomyPicture

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                Toast.makeText(this, "Добавлен в избранное", Toast.LENGTH_SHORT).show()
                viewModel.insertPicture(astronomyPicture)
            }
            R.id.delete -> {
                Toast.makeText(this, "Удалено из избранное", Toast.LENGTH_SHORT).show()
                viewModel.deletePicture(astronomyPicture)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}