package com.dreamsoftware.saborytv.ui.screens.home

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface HomeScreenActionListener: IFudgeTvScreenActionListener {

    fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO)
    fun onCategorySelected(categoryId: String)
}