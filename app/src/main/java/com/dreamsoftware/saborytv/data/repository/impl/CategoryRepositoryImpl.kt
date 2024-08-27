package com.dreamsoftware.saborytv.data.repository.impl

import com.dreamsoftware.saborytv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchCategoryByIdRemoteException
import com.dreamsoftware.saborytv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.saborytv.domain.exception.FetchCategoriesException
import com.dreamsoftware.saborytv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.repository.ICategoryRepository
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class CategoryRepositoryImpl(
    private val categoryRemoteDataSource: ICategoryRemoteDataSource,
    private val categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    override suspend fun getCategories(): List<CategoryBO> = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategories()
                .let(categoryMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching categories", ex)
        }
    }

    @Throws(FetchCategoryByIdException::class)
    override suspend fun getCategoryById(id: String): CategoryBO = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategoryById(id)
                .let(categoryMapper::mapInToOut)
        } catch (ex: FetchCategoryByIdRemoteException) {
            throw FetchCategoryByIdException("An error occurred when fetching category by $id", ex)
        }
    }
}