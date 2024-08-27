package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.saborytv.domain.model.InstructorBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class InstructorMapper : IOneSideMapper<InstructorDTO, InstructorBO> {

    override fun mapInToOut(input: InstructorDTO): InstructorBO = with(input) {
        InstructorBO(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<InstructorDTO>): Iterable<InstructorBO> =
        input.map(::mapInToOut)
}