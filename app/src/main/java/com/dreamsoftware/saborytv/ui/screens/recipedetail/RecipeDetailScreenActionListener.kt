package com.dreamsoftware.saborytv.ui.screens.recipedetail

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface RecipeDetailScreenActionListener: IFudgeTvScreenActionListener {

    fun onRecipeStarted()
    fun onRecipeMoreInfoRequested()
    fun onRecipeFavoriteClicked()
}