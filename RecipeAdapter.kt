package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ItemRecipeBinding
import com.example.recipeapp.model.Recipe
import android.widget.Button
import com.example.recipeapp.R
import com.example.recipeapp.viewmodel.RecipesViewModel



class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val viewModel: RecipesViewModel
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // Define an interface for handling favorite button clicks
    interface OnFavoriteClickListener {
        fun onFavoriteClicked(recipe: Recipe)
    }

    private var onFavoriteClickListener: OnFavoriteClickListener? = null

    fun setOnFavoriteClickListener(listener: OnFavoriteClickListener) {
        onFavoriteClickListener = listener
    }
    class RecipeViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.apply {
            textViewTitle.text = recipe.title
            textViewDescription.text = recipe.description
            imageView.setImageResource(recipe.imageResId)

            // Set initial heart icon based on the favorite state from the recipe
            updateHeartIcon(recipe.isFavorite, favoriteButton)

            favoriteButton.setOnClickListener {
                // Toggle the favorite status in the ViewModel
                viewModel.toggleFavorite(recipe.id)
                recipe.isFavorite = !recipe.isFavorite  // Update local state
                updateHeartIcon(recipe.isFavorite, favoriteButton)  // Update the icon based on the new state
            }
        }
    }
    override fun getItemCount(): Int = recipes.size

        private fun updateHeartIcon(isFavorite: Boolean, button: Button) {
            if (isFavorite) {
                button.setBackgroundResource(R.drawable.heart_shape)
            } else {
                button.setBackgroundResource(R.drawable.heart_shape_outline)
            }
        }
    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

}



