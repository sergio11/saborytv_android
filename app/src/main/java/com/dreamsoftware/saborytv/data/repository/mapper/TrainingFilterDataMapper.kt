package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.saborytv.domain.model.ClassLanguageEnum
import com.dreamsoftware.saborytv.domain.model.IntensityEnum
import com.dreamsoftware.saborytv.domain.model.SortTypeEnum
import com.dreamsoftware.saborytv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.saborytv.domain.model.VideoLengthEnum
import com.dreamsoftware.saborytv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> {
    override fun mapInToOut(input: TrainingFilterDataBO): TrainingFilterDTO = with(input) {
        TrainingFilterDTO(
            classLanguage = classLanguage.takeIf { it != ClassLanguageEnum.NOT_SET }?.value,
            workoutType = workoutType.takeIf { it != WorkoutTypeEnum.NOT_SET }?.value,
            intensity = intensity.takeIf { it != IntensityEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            instructor = instructor.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingFilterDataBO>): Iterable<TrainingFilterDTO> =
        input.map(::mapInToOut)
}