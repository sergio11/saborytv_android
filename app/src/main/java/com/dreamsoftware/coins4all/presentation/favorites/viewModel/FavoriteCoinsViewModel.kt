package com.dreamsoftware.coins4all.presentation.favorites.viewModel

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.dreamsoftware.coins4all.domain.model.Coin
import com.dreamsoftware.coins4all.domain.usecase.GetFavoriteCoinsUseCase
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import com.dreamsoftware.coins4all.presentation.ui.utils.UIState
import javax.inject.Inject

@HiltViewModel
class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase
) : ViewModel() {

    @ExperimentalMaterial3Api
    val scrollBehavior = mutableStateOf(TopAppBarDefaults.pinnedScrollBehavior(TopAppBarState(0.0F,0.0F,0.0F)))

    private val _coinListState = mutableStateOf<UIState<List<Coin>>>(UIState.Empty())
    val coinListState: State<UIState<List<Coin>>> = _coinListState

    init {
        reload()
    }

    fun reload() {
        getFavoriteCoins()
    }

    /**
     * Private Methods
     */

    private fun getFavoriteCoins() {
        getFavoriteCoinsUseCase().onEach { result ->
            _coinListState.value = when (result) {
                is Action.Success -> {
                    UIState.Success(result.data ?: emptyList())
                }
                is Action.Empty -> {
                    UIState.Empty(result.message ?: "Unexpected error")
                }
                is Action.Loading -> {
                    UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

}