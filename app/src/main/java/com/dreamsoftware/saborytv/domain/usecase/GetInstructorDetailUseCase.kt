package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.repository.IChefProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetInstructorDetailUseCase(
    private val instructorRepository: IChefProfilesRepository
): FudgeTvUseCaseWithParams<GetInstructorDetailUseCase.Params, ChefProfileBO>() {

    override suspend fun onExecuted(params: Params): ChefProfileBO =
        instructorRepository.getChefProfileById(params.id)

    data class Params(
        val id: String
    )
}