package com.example.recipeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.R
import com.example.recipeapp.model.Recipe

class RecipesViewModel : ViewModel() {
    // Initial list of recipes, stored to reset after clearing search
    private val initialRecipes = listOf(
        Recipe(
            1, "Chocolate Cake", "Delicious rich chocolate cake", imageResId = R.drawable.chocolate_cake, false),
        Recipe(2, "Spaghetti Bolognese", "Traditional Italian pasta dish with a tomato meat sauce", imageResId = R.drawable.spaghetti_bolognese, false)
    )

    // MutableLiveData to allow updates
    private val _recipes = MutableLiveData<List<Recipe>>(initialRecipes)
    val recipes: LiveData<List<Recipe>> = _recipes

    fun resetRecipes() {
        _recipes.value = initialRecipes
    }

    fun filterRecipes(query: String?) {
        _recipes.value = if (query.isNullOrBlank()) {
            initialRecipes // Reset to full list when there is no search query
        } else {
            initialRecipes.filter {
                it.title.contains(
                    query,
                    ignoreCase = true
                )
            } // Filter based on the query
        }
    }

    fun toggleFavorite(recipeId: Int) {
        val newRecipes = _recipes.value?.map { recipe ->
            if (recipe.id == recipeId) {
                recipe.copy(isFavorite = !recipe.isFavorite)
            } else {
                recipe
            }
        } ?: return // Return if recipes LiveData is null

        // Check if the list of recipes has actually changed before updating LiveData
        if (_recipes.value != newRecipes) {
            _recipes.value = newRecipes
        }
    }
}
