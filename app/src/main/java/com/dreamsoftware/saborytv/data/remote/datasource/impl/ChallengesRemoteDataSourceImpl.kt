package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class ChallengesRemoteDataSourceImpl(
    firebaseStore: FirebaseFirestore,
    challengeMapper: IOneSideMapper<Map<String, Any?>, ChallengeDTO>,
    dispatcher: CoroutineDispatcher
): TrainingProgramRemoteDataSourceImpl<ChallengeDTO>(COLLECTION_NAME, firebaseStore, challengeMapper, dispatcher), IChallengesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "challenges"
    }
}