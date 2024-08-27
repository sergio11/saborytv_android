package com.dreamsoftware.saborytv.domain.validation.impl

import com.dreamsoftware.saborytv.domain.extensions.isEmailNotValid
import com.dreamsoftware.saborytv.domain.extensions.isPasswordNotValid
import com.dreamsoftware.saborytv.domain.model.SignInBO
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.saborytv.domain.validation.ISignInValidationMessagesResolver

internal class SignInValidatorImpl(
    private val messagesResolver: ISignInValidationMessagesResolver
): IBusinessEntityValidator<SignInBO> {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    override fun validate(entity: SignInBO): Map<String, String> = buildMap {
        with(entity) {
            if (email.isEmailNotValid()) {
                put(SignInBO.FIELD_EMAIL, messagesResolver.getInvalidEmailMessage())
            }
            if (password.isPasswordNotValid()) {
                put(SignInBO.FIELD_PASSWORD, messagesResolver.getShortPasswordMessage(MIN_PASSWORD_LENGTH))
            }
        }
    }
}