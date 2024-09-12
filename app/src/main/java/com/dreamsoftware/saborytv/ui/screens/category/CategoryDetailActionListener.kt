package com.dreamsoftware.saborytv.ui.screens.category

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.saborytv.domain.model.RecipeBO

interface CategoryDetailActionListener: IFudgeTvScreenActionListener {

    fun onRecipeOpened(recipeBO: RecipeBO)
}