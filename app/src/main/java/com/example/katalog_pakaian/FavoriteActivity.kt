package com.example.katalog_pakaian

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        bottomNavigation =
            findViewById(R.id.bottomNavigation)

        // pilih menu favorite
        bottomNavigation.selectedItemId =
            R.id.nav_favorite

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

                    true
                }

                R.id.nav_profile -> {

                    startActivity(
                        Intent(
                            this,
                            ProfileActivity::class.java
                        )
                    )

                    true
                }

                else -> false
            }
        }
    }
}