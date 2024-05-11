package com.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapter.RecipeAdapter
import com.example.recipeapp.databinding.FragmentHomeScreenBinding
import com.example.recipeapp.viewmodel.RecipesViewModel
import com.example.recipeapp.model.Recipe

class HomeScreen : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the ViewModel from the activity scope
        viewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        viewModel.resetRecipes()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecipeAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = adapter

        adapter.setOnFavoriteClickListener(object : RecipeAdapter.OnFavoriteClickListener {
            override fun onFavoriteClicked(recipe: Recipe) {
                viewModel.toggleFavorite(recipe.id)
            }
        })

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.updateData(recipes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding when the view is destroyed to avoid memory leaks
    }
}
