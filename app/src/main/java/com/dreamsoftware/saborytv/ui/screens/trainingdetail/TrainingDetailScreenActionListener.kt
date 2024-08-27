package com.dreamsoftware.saborytv.ui.screens.trainingdetail

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface TrainingDetailScreenActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramStarted()
    fun onTrainingProgramMoreInfoRequested()
    fun onTrainingFavoriteClicked()
}