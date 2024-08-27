package com.dreamsoftware.saborytv.di

import android.content.Context
import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.domain.model.SignInBO
import com.dreamsoftware.saborytv.domain.model.SignUpBO
import com.dreamsoftware.saborytv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.saborytv.domain.validation.ICreateProfileRequestValidatorMessagesResolver
import com.dreamsoftware.saborytv.domain.validation.ISignInValidationMessagesResolver
import com.dreamsoftware.saborytv.domain.validation.ISignUpValidationMessagesResolver
import com.dreamsoftware.saborytv.domain.validation.IUpdateProfileRequestValidatorMessagesResolver
import com.dreamsoftware.saborytv.domain.validation.impl.CreateProfileRequestValidatorImpl
import com.dreamsoftware.saborytv.domain.validation.impl.SignInValidatorImpl
import com.dreamsoftware.saborytv.domain.validation.impl.SignUpValidatorImpl
import com.dreamsoftware.saborytv.domain.validation.impl.UpdateProfileRequestValidatorImpl
import com.dreamsoftware.saborytv.ui.validation.CreateProfileRequestValidatorMessagesResolverImpl
import com.dreamsoftware.saborytv.ui.validation.SignInValidationMessagesResolverImpl
import com.dreamsoftware.saborytv.ui.validation.SignUpValidationMessagesResolverImpl
import com.dreamsoftware.saborytv.ui.validation.UpdateProfileRequestValidatorMessagesResolverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ValidatorModule {

    @Provides
    @ViewModelScoped
    fun provideSignUpValidationMessagesResolver(
        @ApplicationContext context: Context
    ): ISignUpValidationMessagesResolver = SignUpValidationMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideSignInValidationMessagesResolver(
        @ApplicationContext context: Context
    ): ISignInValidationMessagesResolver = SignInValidationMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideCreateProfileRequestValidatorMessagesResolver(
        @ApplicationContext context: Context
    ): ICreateProfileRequestValidatorMessagesResolver =
        CreateProfileRequestValidatorMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideIUpdateProfileRequestValidatorMessagesResolver(
        @ApplicationContext context: Context
    ): IUpdateProfileRequestValidatorMessagesResolver =
        UpdateProfileRequestValidatorMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideSignUpValidator(
        messagesResolver: ISignUpValidationMessagesResolver
    ): IBusinessEntityValidator<SignUpBO> = SignUpValidatorImpl(messagesResolver)

    @Provides
    @ViewModelScoped
    fun provideSignInValidator(
        messagesResolver: ISignInValidationMessagesResolver
    ): IBusinessEntityValidator<SignInBO> = SignInValidatorImpl(messagesResolver)

    @Provides
    @ViewModelScoped
    fun provideCreateProfileRequestValidator(
        messagesResolver: ICreateProfileRequestValidatorMessagesResolver
    ): IBusinessEntityValidator<CreateProfileRequestBO> =
        CreateProfileRequestValidatorImpl(messagesResolver)

    @Provides
    @ViewModelScoped
    fun provideUpdateProfileRequestValidator(
        messagesResolver: IUpdateProfileRequestValidatorMessagesResolver
    ): IBusinessEntityValidator<UpdatedProfileRequestBO> =
        UpdateProfileRequestValidatorImpl(messagesResolver)
}