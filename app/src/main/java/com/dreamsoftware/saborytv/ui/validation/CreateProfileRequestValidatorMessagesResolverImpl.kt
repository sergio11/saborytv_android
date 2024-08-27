package com.dreamsoftware.saborytv.ui.validation

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.validation.ICreateProfileRequestValidatorMessagesResolver

class CreateProfileRequestValidatorMessagesResolverImpl(private val context: Context) :
    ICreateProfileRequestValidatorMessagesResolver {
    override fun getInvalidPinMessage(): String = context.getString(R.string.invalid_secure_pin_message)

    override fun getInvalidAliasMessage(): String = context.getString(R.string.invalid_alias_message)
}