package com.example.recipeapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapter.RecipeAdapter
import com.example.recipeapp.databinding.FragmentSearchScreenBinding
import com.example.recipeapp.viewmodel.RecipesViewModel


class searchScreen : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding
    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupSearchEditText()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecipeAdapter(emptyList(), viewModel)
        binding.recyclerView.adapter = adapter
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.updateData(recipes)
        }
    }

    private fun setupSearchEditText() {
        binding.searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.filterRecipes(binding.searchEditText.text.toString())
                true
            } else {
                false
            }
        }
    }
}
