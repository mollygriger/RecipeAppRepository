package com.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipeapp.model.Recipe
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val favoritesList = mutableListOf<Recipe>()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun updateFavorites(recipe: Recipe) {
        // Check if the recipe is already in the favorites list
        val existingFavorite = favoritesList.find { it.id == recipe.id }

        if (existingFavorite != null) {
            // If the recipe is already favorited, remove it from the favorites list (unfavorite)
            favoritesList.remove(existingFavorite)
        } else {
            // If the recipe is not favorited, add it to the favorites list
            favoritesList.add(recipe)
        }
    }

    fun getFavoritesList(): List<Recipe> {
        return favoritesList.toList()
    }
}




