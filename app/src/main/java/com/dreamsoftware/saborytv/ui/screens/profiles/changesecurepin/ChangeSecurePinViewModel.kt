package com.dreamsoftware.saborytv.ui.screens.profiles.changesecurepin

import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.usecase.ChangeSecurePinUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.saborytv.utils.combinedLet
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeSecurePinViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val changeSecurePinUseCase: ChangeSecurePinUseCase
): FudgeTvViewModel<ChangeSecurePinUiState, ChangeSecurePinSideEffects>(), ChangeSecurePinActionListener {

    override fun onGetDefaultState(): ChangeSecurePinUiState = ChangeSecurePinUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState { it.copy(profile = profileBO) }
    }

    override fun onConfirmPressed() {
        uiState.value.let {
            combinedLet(it.currentSecurePin.toIntOrNull(), it.newSecurePin.toIntOrNull()) { currentSecurePin, newSecurePin ->
                executeUseCaseWithParams(
                    useCase = changeSecurePinUseCase,
                    params = ChangeSecurePinUseCase.Params(
                        profileId = it.profile?.uuid.orEmpty(),
                        currentSecurePin = currentSecurePin,
                        newSecurePin = newSecurePin
                    ),
                    onSuccess = { onSecurePinChanged() }
                )
            }
        }
    }

    override fun onDeleteProfilePressed() {
        uiState.value.profile?.let {
            launchSideEffect(ChangeSecurePinSideEffects.RequestDeleteProfile(it.uuid))
        }
    }

    override fun onCurrentSecurePinChanged(pin: String) {
        updateState { it.copy(currentSecurePin = pin) }
    }

    override fun onNewSecurePinChanged(pin: String) {
        updateState { it.copy(newSecurePin = pin) }
    }

    override fun onCloseSecurePinUpdatedDialog() {
        updateState { it.copy(showSecurePinUpdatedDialog = false) }
        launchSideEffect(ChangeSecurePinSideEffects.SecurePinUpdated)
    }

    private fun onSecurePinChanged() {
        updateState {
            it.copy(
                showSecurePinUpdatedDialog = true,
                currentSecurePinError = String.EMPTY,
                currentSecurePin = String.EMPTY,
                newSecurePin = String.EMPTY,
                newSecurePinError = String.EMPTY
            )
        }
    }
}

data class ChangeSecurePinUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val showSecurePinUpdatedDialog: Boolean = false,
    val profile: ProfileBO? = null,
    val currentSecurePin: String = String.EMPTY,
    val currentSecurePinError: String = String.EMPTY,
    val newSecurePin: String = String.EMPTY,
    val newSecurePinError: String = String.EMPTY,
): UiState<ChangeSecurePinUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ChangeSecurePinUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ChangeSecurePinSideEffects: SideEffect {
    data class RequestDeleteProfile(val id: String): ChangeSecurePinSideEffects
    data object SecurePinUpdated: ChangeSecurePinSideEffects
}