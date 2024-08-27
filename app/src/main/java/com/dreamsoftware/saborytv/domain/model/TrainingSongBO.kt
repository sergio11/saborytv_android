package com.dreamsoftware.saborytv.domain.model

data class TrainingSongBO(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val audioUrl: String,
    val author: String
)
