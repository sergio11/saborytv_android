package com.dreamsoftware.saborytv.domain.model

import com.dreamsoftware.saborytv.ui.utils.EMPTY

enum class IntensityEnum(val value: String, val level: String) {
    NOT_SET("Not set", String.EMPTY),
    EASY("Easy", "Intensity •"),
    MEDIUM("Medium", "Intensity ••"),
    HARD("Hard", "Intensity •••")
}