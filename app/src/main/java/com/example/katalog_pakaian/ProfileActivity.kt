package com.example.katalog_pakaian

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigation =
            findViewById(R.id.bottomNavigation)

        // pilih menu profile
        bottomNavigation.selectedItemId =
            R.id.nav_profile

        bottomNavigation.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.nav_home -> {

                    startActivity(
                        Intent(
                            this,
                            MainActivity::class.java
                        )
                    )

                    true
                }

                R.id.nav_favorite -> {

                    startActivity(
                        Intent(
                            this,
                            FavoriteActivity::class.java
                        )
                    )

                    true
                }

                R.id.nav_profile -> {

                    true
                }

                else -> false
            }
        }
    }
}