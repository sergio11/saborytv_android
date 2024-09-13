package com.dreamsoftware.saborytv.ui.screens.recipes

import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.model.SortTypeEnum
import com.dreamsoftware.saborytv.domain.model.VideoLengthEnum
import com.dreamsoftware.saborytv.domain.usecase.GetChefProfilesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesByTypeUseCase
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.fudge.component.menu.FudgeTvFilterVO
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.fudge.utils.IFudgeTvApplicationAware
import com.dreamsoftware.saborytv.domain.model.DifficultyEnum
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getChefProfilesUseCase: GetChefProfilesUseCase,
    private val getRecipesByTypeUseCase: GetRecipesByTypeUseCase,
    private val applicationAware: IFudgeTvApplicationAware,
    @FavoritesScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<RecipesUiState, RecipesSideEffects>(), RecipesScreenActionListener {

    private var chefProfiles: List<ChefProfileBO> = emptyList()
    private var chefProfile: String = String.EMPTY
    private var videoLength: VideoLengthEnum = VideoLengthEnum.NOT_SET
    private var difficulty: DifficultyEnum = DifficultyEnum.NOT_SET
    private var classLanguage: LanguageEnum = LanguageEnum.NOT_SET
    private var sortType: SortTypeEnum = SortTypeEnum.NOT_SET

    override fun onGetDefaultState(): RecipesUiState = RecipesUiState(
        filterItems = listOf(
            FudgeTvFilterVO(
                id = VIDEO_LENGTH_FILTER,
                icon = R.drawable.length_ic,
                title = R.string.length,
                description = VideoLengthEnum.NOT_SET.value,
                options = VideoLengthEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = CLASS_LANGUAGE_FILTER,
                icon = R.drawable.language_ic,
                title = R.string.class_language,
                description = LanguageEnum.NOT_SET.value,
                options = LanguageEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = DIFFICULTY_FILTER,
                icon = R.drawable.difficulty_ic,
                title = R.string.difficulty,
                description = DifficultyEnum.NOT_SET.value,
                options = DifficultyEnum.entries.map { it.value }
            ),
            FudgeTvFilterVO(
                id = CHEF_PROFILE_FILTER,
                icon = R.drawable.ic_chef_profile,
                title = R.string.chef_profile
            )
        )
    )

    fun fetchData() {
        fetchRecipes()
    }

    override fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    override fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    override fun onSortCleared() {
        sortType = SortTypeEnum.NOT_SET
        updateState { it.copy(selectedSortItem = 0, isSortExpended = false) }
        fetchRecipes()
    }

    override fun onDismissSortSideMenu() {
        updateState { it.copy(isSortExpended = false) }
    }

    override fun onDismissFilterSideMenu() {
        updateState { it.copy(isFilterExpended = false) }
    }

    override fun onFilterCleared() {
        resetFilters()
        fetchRecipes()
    }

    override fun onDismissFieldFilterSideMenu() {
        updateState { it.copy(isFieldFilterSelected = false) }
    }

    override fun onFilterFieldSelected(trainingFilter: FudgeTvFilterVO) {
        updateState {
            it.copy(
                selectedTrainingFilter = trainingFilter,
                isFieldFilterSelected = true
            )
        }
    }

    override fun onSelectedSortedItem(currentIndex: Int) {
        sortType = SortTypeEnum.entries[currentIndex]
        updateState { it.copy(selectedSortItem = currentIndex, isSortExpended = false) }
        fetchRecipes()
    }

    override fun onSelectedFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedTrainingFilter?.let { filter ->
            when(filter.id) {
                VIDEO_LENGTH_FILTER -> {
                    videoLength = VideoLengthEnum.entries[currentIndex]
                }
                DIFFICULTY_FILTER -> {
                    difficulty = DifficultyEnum.entries[currentIndex]
                }
                CLASS_LANGUAGE_FILTER -> {
                    classLanguage = LanguageEnum.entries[currentIndex]
                }
                CHEF_PROFILE_FILTER -> {
                    chefProfile = chefProfiles.getOrNull(currentIndex)?.id.orEmpty()
                }
            }
            updateState {
                it.copy(
                    filterItems = it.filterItems.map { item ->
                        if(item.id == filter.id) {
                            item.copy(
                                selectedOption = currentIndex,
                                description = when(filter.id) {
                                    VIDEO_LENGTH_FILTER -> VideoLengthEnum.entries[currentIndex].value
                                    DIFFICULTY_FILTER -> DifficultyEnum.entries[currentIndex].value
                                    CLASS_LANGUAGE_FILTER -> LanguageEnum.entries[currentIndex].value
                                    else -> chefProfiles.getOrNull(currentIndex)?.name.orEmpty()
                                }
                            )
                        } else {
                            item
                        }
                    },
                    selectedTrainingFilter = null
                )
            }
            fetchRecipes()
        }
    }

    override fun onChangeSelectedTab(index: Int) {
        updateState {
            it.copy(
                selectedTab = index,
                recipes = emptyList(),
                typeSelected = RecipeTypeEnum.entries[index]
            )
        }
        fetchRecipes()
    }

    override fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }

    override fun onItemClicked(id: String) {
        launchSideEffect(
            RecipesSideEffects.OpenRecipeProgramDetail(id = id)
        )
    }

    private fun fetchChefProfiles() {
        executeUseCase(useCase = getChefProfilesUseCase, onSuccess = ::onGetChefProfilesSuccessfully)
    }

    private fun fetchRecipes() {
        executeUseCaseWithParams(
            useCase = getRecipesByTypeUseCase,
            params = GetRecipesByTypeUseCase.Params(
                type = uiState.value.typeSelected,
                classLanguage = classLanguage,
                difficulty = difficulty,
                videoLength = videoLength,
                sortType = sortType,
                instructor = chefProfile
            ),
            onSuccess = ::onGetRecipesSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    private fun onGetRecipesSuccessfully(trainingPrograms: List<RecipeBO>) {
        updateState { it.copy(recipes = trainingPrograms) }
        if(chefProfiles.isEmpty()) {
            fetchChefProfiles()
        }
    }

    private fun onGetChefProfilesSuccessfully(chefProfileList: List<ChefProfileBO>) {
        chefProfiles = chefProfileList
        val noChefProfileSet = applicationAware.getString(R.string.no_chef_profile_set)
        updateState {
            it.copy(
                filterItems = it.filterItems.map { item ->
                    if(item.id == CHEF_PROFILE_FILTER) {
                        item.copy(
                            options = chefProfileList.map(ChefProfileBO::name) + noChefProfileSet,
                            description = noChefProfileSet
                        )
                    } else {
                        item
                    }
                }
            )
        }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: RecipesUiState) =
        uiState.copy(
            isLoading = false,
            recipes = emptyList(),
            errorMessage = errorMapper.mapToMessage(ex)
        )

    private fun resetFilters() {
        videoLength = VideoLengthEnum.NOT_SET
        difficulty = DifficultyEnum.NOT_SET
        classLanguage = LanguageEnum.NOT_SET
        chefProfile = String.EMPTY
        updateState {
            it.copy(
                isFilterExpended = false,
                filterItems = it.filterItems.resetOptions()
            )
        }
    }

    private fun List<FudgeTvFilterVO>.resetOptions() = map { item ->
        item.copy(
            selectedOption = 0,
            description = when(item.id) {
                VIDEO_LENGTH_FILTER -> VideoLengthEnum.NOT_SET.value
                DIFFICULTY_FILTER -> DifficultyEnum.NOT_SET.value
                CLASS_LANGUAGE_FILTER -> LanguageEnum.NOT_SET.value
                else -> String.EMPTY
            }
        )
    }
}

data class RecipesUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isFieldFilterSelected: Boolean = false,
    val isSortExpended: Boolean = false,
    val recipes: List<RecipeBO> = emptyList(),
    val filterItems: List<FudgeTvFilterVO> = emptyList(),
    val selectedSortItem: Int = 0,
    val selectedTrainingFilter: FudgeTvFilterVO? = null,
    val selectedTab: Int = 0,
    val tabsTitle: List<Int> = listOf(
        R.string.recipe_type_vegetarian_name,
        R.string.recipe_type_vegan_name,
        R.string.recipe_type_gluten_free_name,
        R.string.recipe_type_high_protein_name,
    ),
    val typeSelected: RecipeTypeEnum = RecipeTypeEnum.VEGETARIAN,
    val focusTabIndex: Int = 0,
) : UiState<RecipesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): RecipesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface RecipesSideEffects : SideEffect {
    data class OpenRecipeProgramDetail(val id: String) : RecipesSideEffects
}

const val VIDEO_LENGTH_FILTER = "VIDEO_LENGTH_FILTER"
const val DIFFICULTY_FILTER = "DIFFICULTY_FILTER"
const val CLASS_LANGUAGE_FILTER = "CLASS_LANGUAGE_FILTER"
const val CHEF_PROFILE_FILTER = "CHEF_PROFILE_FILTER"