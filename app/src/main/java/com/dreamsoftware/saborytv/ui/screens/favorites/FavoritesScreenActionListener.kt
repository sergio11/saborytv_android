package com.dreamsoftware.saborytv.ui.screens.favorites

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.saborytv.domain.model.RecipeBO

interface FavoritesScreenActionListener: IFudgeTvScreenActionListener {

    fun onRecipeProgramSelected(recipe: RecipeBO)
    fun onRecipeProgramStarted(id: String)
    fun onRecipeProgramRemovedFromFavorites(id: String)
    fun onDismissRequest()
}