package com.dreamsoftware.saborytv.ui.screens.category

import com.dreamsoftware.saborytv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface CategoryDetailActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO)
}