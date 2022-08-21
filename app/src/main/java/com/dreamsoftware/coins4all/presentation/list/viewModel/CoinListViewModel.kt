package com.dreamsoftware.coins4all.presentation.list.viewModel

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
import com.dreamsoftware.coins4all.domain.model.Overview
import com.dreamsoftware.coins4all.domain.usecase.GetCoinsUseCase
import com.dreamsoftware.coins4all.domain.usecase.GetOverviewUseCase
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import com.dreamsoftware.coins4all.presentation.ui.utils.UIState
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getOverviewUseCase: GetOverviewUseCase
) : ViewModel() {

    @ExperimentalMaterial3Api
    val scrollBehavior = mutableStateOf(TopAppBarDefaults.pinnedScrollBehavior(TopAppBarState(0.0F,0.0F,0.0F)))

    private val _coinListState = mutableStateOf<UIState<List<Coin>>>(UIState.Empty())
    val coinListState: State<UIState<List<Coin>>> = _coinListState

    private val _overviewState = mutableStateOf<UIState<Overview?>>(UIState.Empty())
    val overviewState: State<UIState<Overview?>> = _overviewState

    private val _dialogState = mutableStateOf(false)
    val dialogState: State<Boolean> = _dialogState

    private var currentList: List<Coin> = listOf()

    init {
        reload()
    }

    fun filterFor(query: String) {
        _coinListState.value = UIState.Success(
            if (query.trim().isNotEmpty()) currentList.filter {
                it.name.lowercase().contains(query) or it.symbol.lowercase()
                    .contains(query) or it.rank.toString().contains(query)
            } else currentList
        )
    }

    fun showDialog(value: Boolean) {
        _dialogState.value = value
    }

    fun reload() {
        getOverview()
        getCoins()
    }

    /**
     * Private Methods
     */

    private fun getOverview() {
        getOverviewUseCase().onEach { result ->
            _overviewState.value = when (result) {
                is Action.Success -> {
                    UIState.Success(result.data)
                }
                is Action.Empty -> {
                    UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            _coinListState.value = when (result) {
                is Action.Success -> {
                    UIState.Success(result.data ?: emptyList())
                }
                is Action.Empty -> {
                    UIState.Empty(result.message)
                }
                is Action.Loading -> {
                    UIState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }
}