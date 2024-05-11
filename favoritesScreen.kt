package com.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapter.RecipeAdapter
import com.example.recipeapp.databinding.FragmentFavoritesScreenBinding
import com.example.recipeapp.viewmodel.RecipesViewModel
import androidx.recyclerview.widget.RecyclerView

class favoritesScreen : Fragment() {

    private var _binding: FragmentFavoritesScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecipesViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        adapter = RecipeAdapter(emptyList(), viewModel)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@favoritesScreen.adapter
        }

        // Observe the LiveData from ViewModel for favorited recipes
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.updateData(recipes.filter { it.isFavorite })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Properly clean up the binding to avoid memory leaks
    }
}
