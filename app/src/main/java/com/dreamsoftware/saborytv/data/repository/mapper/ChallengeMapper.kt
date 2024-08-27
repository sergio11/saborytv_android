package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.saborytv.domain.model.ChallengeBO
import com.dreamsoftware.saborytv.domain.model.ChallengeWeaklyPlansBO
import com.dreamsoftware.saborytv.domain.model.IntensityEnum
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.WorkoutBO
import com.dreamsoftware.saborytv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.dreamsoftware.saborytv.utils.enumNameOfOrDefault

internal class ChallengeMapper(
    private val workoutMapper: IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO>
) : IOneSideMapper<Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>, ChallengeBO> {

    override fun mapInToOut(input: Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>): ChallengeBO = with(input) {
        ChallengeBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = third.name,
            instructorId = third.id,
            workoutType = enumNameOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            duration = first.duration,
            videoUrl = first.videoUrl,
            intensity = enumNameOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            language = enumNameOfOrDefault(first.language, LanguageEnum.ENGLISH),
            numberOfDays = first.numberOfDays,
            song = first.song,
            isPremium = first.isPremium,
            weaklyPlans = first.weaklyPlans.map { weaklyPlan ->
                ChallengeWeaklyPlansBO(
                    name = weaklyPlan.name,
                    workouts = weaklyPlan.workouts.mapNotNull {  workout ->
                        second.find { it.id == workout }
                    }.map { it to third }.map(workoutMapper::mapInToOut)
                )
            }
        )
    }

    override fun mapInListToOutList(input: Iterable<Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>>): Iterable<ChallengeBO> =
        input.map(::mapInToOut)
}