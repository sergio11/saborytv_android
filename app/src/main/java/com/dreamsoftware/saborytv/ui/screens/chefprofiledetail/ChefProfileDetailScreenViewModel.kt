package com.dreamsoftware.saborytv.ui.screens.chefprofiledetail

import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.usecase.GetChefProfileDetailUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChefProfileDetailScreenViewModel @Inject constructor(
    private val getChefProfileDetailUseCase: GetChefProfileDetailUseCase
) : FudgeTvViewModel<ChefProfileDetailUiState, ChefProfileDetailSideEffects>(), IChefProfileDetailActionListener {

    override fun onGetDefaultState(): ChefProfileDetailUiState = ChefProfileDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getChefProfileDetailUseCase,
            params = GetChefProfileDetailUseCase.Params(id),
            onSuccess = ::onGetChefProfileDetailsCompleted
        )
    }

    private fun onGetChefProfileDetailsCompleted(chefProfile: ChefProfileBO) {
        updateState { it.copy(chefProfileDetail = chefProfile) }
    }

    override fun onBackPressed() {
        launchSideEffect(ChefProfileDetailSideEffects.CloseDetail)
    }
}

data class ChefProfileDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val chefProfileDetail: ChefProfileBO? = null
): UiState<ChefProfileDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ChefProfileDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ChefProfileDetailSideEffects : SideEffect {
    data object CloseDetail: ChefProfileDetailSideEffects
}