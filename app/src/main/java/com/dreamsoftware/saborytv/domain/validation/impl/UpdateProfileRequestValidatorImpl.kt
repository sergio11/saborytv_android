package com.dreamsoftware.saborytv.domain.validation.impl

import com.dreamsoftware.saborytv.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.saborytv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.saborytv.domain.validation.IUpdateProfileRequestValidatorMessagesResolver

internal class UpdateProfileRequestValidatorImpl(
    private val messagesResolver: IUpdateProfileRequestValidatorMessagesResolver
): IBusinessEntityValidator<UpdatedProfileRequestBO> {

    override fun validate(entity: UpdatedProfileRequestBO): Map<String, String> = buildMap {
        with(entity) {
            if(alias != null && alias.isProfileAliasNotValid()) {
                put(UpdatedProfileRequestBO.FIELD_ALIAS, messagesResolver.getInvalidAliasMessage())
            }
        }
    }
}