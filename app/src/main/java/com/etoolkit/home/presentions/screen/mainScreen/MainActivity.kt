package com.etoolkit.home.presentions.screen.mainScreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etoolkit.home.R
import com.etoolkit.home.databinding.ActivityMainBinding
import com.etoolkit.home.presentions.screen.favoriteScreen.FavoriteFragment
import com.etoolkit.home.presentions.screen.homeScreen.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,HomeFragment()).commit()

        bottomNavigationClick()

    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment).commit()
    }

    private fun bottomNavigationClick(){

        binding.bottomNavigation.setOnItemSelectedListener { item ->

            val fragment: Fragment

            when (item.itemId) {

                R.id.home -> {
                    supportActionBar?.setTitle("Астрономическая картинка дня")
                    fragment = HomeFragment()
                    replaceFragment(fragment)
                    true
                }

                R.id.favorite -> {
                    supportActionBar?.setTitle("Favorite")
                    fragment = FavoriteFragment()
                    replaceFragment(fragment)
                    true
                }
                else -> false
            }
        }
    }
}
