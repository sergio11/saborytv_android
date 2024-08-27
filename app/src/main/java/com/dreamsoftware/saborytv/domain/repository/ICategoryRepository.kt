package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.FetchCategoriesException
import com.dreamsoftware.saborytv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.saborytv.domain.model.CategoryBO

interface ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    suspend fun getCategories(): List<CategoryBO>

    @Throws(FetchCategoryByIdException::class)
    suspend fun getCategoryById(id: String): CategoryBO
}