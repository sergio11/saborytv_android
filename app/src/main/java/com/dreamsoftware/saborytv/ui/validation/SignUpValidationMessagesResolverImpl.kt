package com.dreamsoftware.saborytv.ui.validation

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.validation.ISignUpValidationMessagesResolver

class SignUpValidationMessagesResolverImpl(private val context: Context) :
    ISignUpValidationMessagesResolver {
    override fun getInvalidEmailMessage(): String =
        context.getString(R.string.invalid_email_message)

    override fun getShortPasswordMessage(minLength: Int): String =
        context.getString(R.string.short_password_message, minLength)

    override fun getPasswordsDoNotMatchMessage(): String =
        context.getString(R.string.passwords_do_not_match_message)
}