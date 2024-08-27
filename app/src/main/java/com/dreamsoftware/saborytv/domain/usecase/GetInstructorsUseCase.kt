package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.InstructorBO
import com.dreamsoftware.saborytv.domain.repository.IInstructorRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetInstructorsUseCase(
    private val instructorRepository: IInstructorRepository
) : FudgeTvUseCase<List<InstructorBO>>() {
    override suspend fun onExecuted():  List<InstructorBO> =
        instructorRepository.getInstructors()
}