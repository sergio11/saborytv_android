package com.dreamsoftware.saborytv.domain.validation

interface ICreateProfileRequestValidatorMessagesResolver {
    fun getInvalidPinMessage(): String
    fun getInvalidAliasMessage(): String
}