# Recipe App Maintenance Guide

## Overview
Recipe App is a mobile application configured for Android platforms. The application allos for users to browse, favorite, and search recipes.

## Evironment Setup
- **Development IDE**: Android Studio
- **SDK Version**: Compile SDK Version 34
- **Minimum SDK Version**: 24

## Repository Structure
- 'MainActivity.kt': Handles navigation and interactions with the bottom navigation bar.
- 'RecipesViewModel.kt': Manages logic for updating, filtering, and retrieving recipes and their favorites status.
- 'Recipe.kt': Data model for recipes.
- 'HomeScreen.kt, favoritesScreen.kt, searchScreen.kt': Fragments representing each screen within the application.
- 'navHostFragment.kt': Manages navigation between fragments
- 'RecipesViewModelTest.kt': Contains tests for 'RecipesViewModel'
- 'TestUtils.kt': Contains functions to properly test the LiveData implementation

## Utilities
- **LiveData Extensions**: Handles asynchronous LiveData objects for testing.

## Testing
- Use 'InstantTaskExecutorRule' for LiveData synchronization in tests.
- 'RecipesViewModelTest' class tests all ViewModel functionalities.
- Use 'getOrAwaitValue' from 'TestUtils.kt' to test LiveData implementation

## Update Procedures
1. **Pull Requests**: All changes should be submitted via pull requests to the `main` branch.
2. **Code Reviews**: Every pull request must be reviewed by at least one other team member before merging.
3. **Testing**: Update must pass all existing unit tests and, if applicable, new tests should be added.

## Maintenance Contacts
- **Lead Developer**: Molly Griger
- **Contact Email**: molly.griger@csuglobal.edu
