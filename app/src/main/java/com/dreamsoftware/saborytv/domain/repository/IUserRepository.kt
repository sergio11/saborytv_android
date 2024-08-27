package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.GetUserAuthenticatedUidException
import com.dreamsoftware.saborytv.domain.exception.GetUserDetailException
import com.dreamsoftware.saborytv.domain.exception.GetUserPreferencesException
import com.dreamsoftware.saborytv.domain.exception.SaveUserPreferencesException
import com.dreamsoftware.saborytv.domain.exception.SignInException
import com.dreamsoftware.saborytv.domain.exception.SignOffException
import com.dreamsoftware.saborytv.domain.exception.SignUpException
import com.dreamsoftware.saborytv.domain.exception.UpdateUserDetailException
import com.dreamsoftware.saborytv.domain.exception.VerifySessionException
import com.dreamsoftware.saborytv.domain.model.SignInBO
import com.dreamsoftware.saborytv.domain.model.SignUpBO
import com.dreamsoftware.saborytv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.saborytv.domain.model.UserDetailBO
import com.dreamsoftware.saborytv.domain.model.UserPreferenceBO
import kotlin.jvm.Throws

interface IUserRepository {

    @Throws(SignInException::class)
    suspend fun signIn(data: SignInBO): UserDetailBO

    @Throws(SignUpException::class)
    suspend fun signUp(user: SignUpBO): UserDetailBO

    @Throws(SignOffException::class)
    suspend fun signOff()

    @Throws(VerifySessionException::class)
    suspend fun hasSession(): Boolean

    @Throws(GetUserDetailException::class)
    suspend fun getDetail(): UserDetailBO

    @Throws(GetUserAuthenticatedUidException::class)
    suspend fun getAuthenticatedUid(): String

    @Throws(UpdateUserDetailException::class)
    suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO

    @Throws(SaveUserPreferencesException::class)
    suspend fun saveUserPreferences(data: UserPreferenceBO)

    @Throws(GetUserPreferencesException::class)
    suspend fun getUserPreferences(): UserPreferenceBO
}