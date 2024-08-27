package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class CategoryMapper : IOneSideMapper<CategoryDTO, CategoryBO> {

    override fun mapInToOut(input: CategoryDTO): CategoryBO = with(input) {
        CategoryBO(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<CategoryDTO>): Iterable<CategoryBO> =
        input.map(::mapInToOut)
}