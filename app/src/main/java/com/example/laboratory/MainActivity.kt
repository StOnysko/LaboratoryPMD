package com.example.laboratory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.laboratory.application.fragments.CreatePostFragment
import com.example.laboratory.application.fragments.LikedPostsFragment
import com.example.laboratory.application.fragments.MainScreenFragment
import com.example.laboratory.application.fragments.SearchPostFragment
import com.example.laboratory.application.fragments.UserProfileFragment
import com.example.laboratory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentNavigation(MainScreenFragment())

        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.bottomNavBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    fragmentNavigation(MainScreenFragment())
                    true
                }

                R.id.createPost -> {
                    fragmentNavigation(CreatePostFragment())
                    true
                }

                R.id.search -> {
                    fragmentNavigation(SearchPostFragment())
                    true
                }

                R.id.liked -> {
                    fragmentNavigation(LikedPostsFragment())
                    true
                }

                R.id.profile -> {
                    fragmentNavigation(UserProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun fragmentNavigation(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}