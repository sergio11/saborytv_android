package com.dreamsoftware.saborytv.ui.screens.home

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.saborytv.domain.model.RecipeBO

interface HomeScreenActionListener: IFudgeTvScreenActionListener {

    fun onOpenRecipeDetail(recipe: RecipeBO)
    fun onCategorySelected(categoryId: String)
}