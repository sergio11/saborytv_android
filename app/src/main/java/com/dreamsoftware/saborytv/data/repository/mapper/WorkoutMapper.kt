package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.saborytv.domain.model.IntensityEnum
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.WorkoutBO
import com.dreamsoftware.saborytv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.dreamsoftware.saborytv.utils.enumNameOfOrDefault

internal class WorkoutMapper : IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO> {

    override fun mapInToOut(input: Pair<WorkoutDTO, InstructorDTO>): WorkoutBO = with(input) {
        WorkoutBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = second.name,
            instructorId = second.id,
            workoutType = enumNameOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            duration = first.duration,
            videoUrl = first.videoUrl,
            isPremium = first.isPremium,
            intensity = enumNameOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            song = first.song,
            language = enumNameOfOrDefault(first.language, LanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<WorkoutDTO, InstructorDTO>>): Iterable<WorkoutBO> =
        input.map(::mapInToOut)
}