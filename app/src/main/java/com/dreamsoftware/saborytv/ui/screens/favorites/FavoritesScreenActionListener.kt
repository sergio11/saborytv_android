package com.dreamsoftware.saborytv.ui.screens.favorites

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.saborytv.domain.model.RecipeBO

interface FavoritesScreenActionListener: IFudgeTvScreenActionListener {

    fun onRecipeSelected(recipe: RecipeBO)
    fun onRecipeStarted(id: String)
    fun onRecipeRemovedFromFavorites(id: String)
    fun onDismissRequest()
}