package com.dreamsoftware.saborytv.ui.validation

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.validation.ISignInValidationMessagesResolver

class SignInValidationMessagesResolverImpl(private val context: Context) :
    ISignInValidationMessagesResolver {
    override fun getInvalidEmailMessage(): String =
        context.getString(R.string.invalid_email_message)

    override fun getShortPasswordMessage(minLength: Int): String =
        context.getString(R.string.short_password_message, minLength)
}