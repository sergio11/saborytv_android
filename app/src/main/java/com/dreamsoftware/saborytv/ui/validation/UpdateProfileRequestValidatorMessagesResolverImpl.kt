package com.dreamsoftware.saborytv.ui.validation

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.validation.IUpdateProfileRequestValidatorMessagesResolver

class UpdateProfileRequestValidatorMessagesResolverImpl(private val context: Context) :
    IUpdateProfileRequestValidatorMessagesResolver {

    override fun getInvalidAliasMessage(): String = context.getString(R.string.invalid_alias_message)
}