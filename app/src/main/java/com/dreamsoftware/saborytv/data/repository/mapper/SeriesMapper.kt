package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.saborytv.domain.model.IntensityEnum
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.SeriesBO
import com.dreamsoftware.saborytv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.dreamsoftware.saborytv.utils.enumNameOfOrDefault

internal class SeriesMapper : IOneSideMapper<Pair<SeriesDTO, InstructorDTO>, SeriesBO> {

    override fun mapInToOut(input: Pair<SeriesDTO, InstructorDTO>): SeriesBO = with(input) {
        SeriesBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = second.name,
            instructorId = second.id,
            workoutType = enumNameOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            numberOfWeeks = first.numberOfWeeks,
            numberOfClasses = first.numberOfClasses,
            duration = first.duration,
            videoUrl = first.videoUrl,
            intensity = enumNameOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            song = first.song,
            isPremium = first.isPremium,
            language = enumNameOfOrDefault(first.language, LanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<SeriesDTO, InstructorDTO>>): Iterable<SeriesBO> =
        input.map(::mapInToOut)
}