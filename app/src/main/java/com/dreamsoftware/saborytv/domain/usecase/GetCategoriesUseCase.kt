package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.repository.ICategoryRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetCategoriesUseCase(
    private val categoryRepository: ICategoryRepository
) : FudgeTvUseCase<List<CategoryBO>>() {
    override suspend fun onExecuted(): List<CategoryBO> =
        categoryRepository.getCategories()
}