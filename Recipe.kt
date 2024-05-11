package com.example.recipeapp.model

data class Recipe (
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)
