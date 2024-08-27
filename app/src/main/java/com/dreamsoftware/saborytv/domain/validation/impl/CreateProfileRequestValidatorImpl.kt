package com.dreamsoftware.saborytv.domain.validation.impl

import com.dreamsoftware.saborytv.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.saborytv.domain.extensions.isSecurePinNotValid
import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.saborytv.domain.validation.ICreateProfileRequestValidatorMessagesResolver

internal class CreateProfileRequestValidatorImpl(
    private val messagesResolver: ICreateProfileRequestValidatorMessagesResolver
) : IBusinessEntityValidator<CreateProfileRequestBO> {

    override fun validate(entity: CreateProfileRequestBO): Map<String, String> = buildMap {
        with(entity) {
            if (pin != null && pin.isSecurePinNotValid()) {
                put(CreateProfileRequestBO.FIELD_PIN, messagesResolver.getInvalidPinMessage())
            }
            if (alias.isProfileAliasNotValid()) {
                put(CreateProfileRequestBO.FIELD_ALIAS, messagesResolver.getInvalidAliasMessage())
            }
        }
    }
}