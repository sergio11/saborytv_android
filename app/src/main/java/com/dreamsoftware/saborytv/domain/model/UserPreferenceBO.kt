package com.dreamsoftware.saborytv.domain.model

data class UserPreferenceBO(
    val units: UnitsEnum,
    val language: AppLanguageEnum,
    val videoQuality: VideoQualityEnum
)
