package com.example.recipeapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Rule
import org.junit.Assert.*
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RecipesViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipesViewModel

    @Before
    fun setUp() {
        viewModel = RecipesViewModel()
    }

    @After
    fun tearDown() {
        // Consider adding logic to reset or clean up after tests if necessary
    }

    private fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
        val latch = CountDownLatch(1)
        var data: T? = null
        val observer = Observer<T> { o ->
            data = o
            latch.countDown()
        }
        this.observeForever(observer)
        try {
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            this.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }

    @Test
    fun getRecipesInitiallyReturnsInitialRecipes() {
        val recipes = viewModel.recipes.getOrAwaitValue()
        assertNotNull(recipes)
        assertEquals(2, recipes.size)
        assertEquals("Chocolate Cake", recipes[0].title)
    }

    @Test
    fun resetRecipesShouldResetToInitialRecipes() {
        viewModel.resetRecipes()
        val recipes = viewModel.recipes.getOrAwaitValue()
        assertNotNull(recipes)
        assertEquals(2, recipes.size)
        assertEquals("Chocolate Cake", recipes[0].title)
    }

    @Test
    fun filterRecipesWithNonBlankQueryFiltersCorrectly() {
        viewModel.filterRecipes("Spaghetti")
        val recipes = viewModel.recipes.getOrAwaitValue()
        assertNotNull(recipes)
        assertEquals(1, recipes.size)
        assertEquals("Spaghetti Bolognese", recipes[0].title)
    }

    @Test
    fun filterRecipesWithBlankQueryResetsList() {
        viewModel.filterRecipes("")
        val recipes = viewModel.recipes.getOrAwaitValue()
        assertNotNull(recipes)
        assertEquals(2, recipes.size)
    }

    @Test
    fun toggleFavoriteChangesTheFavoriteStateOfARecipe() {
        viewModel.toggleFavorite(1)
        val recipe = viewModel.recipes.getOrAwaitValue().find { it.id == 1 }
        assertNotNull(recipe)
        assertTrue(recipe?.isFavorite ?: false)
    }
}



