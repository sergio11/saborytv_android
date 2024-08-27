package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.InstructorBO
import com.dreamsoftware.saborytv.domain.repository.IInstructorRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetInstructorDetailUseCase(
    private val instructorRepository: IInstructorRepository
): FudgeTvUseCaseWithParams<GetInstructorDetailUseCase.Params, InstructorBO>() {

    override suspend fun onExecuted(params: Params): InstructorBO =
        instructorRepository.getInstructorById(params.id)

    data class Params(
        val id: String
    )
}