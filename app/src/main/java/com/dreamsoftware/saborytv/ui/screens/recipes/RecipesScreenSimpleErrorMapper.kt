package com.dreamsoftware.saborytv.ui.screens.recipes

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.exception.InvalidDataException
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper

class RecipesScreenSimpleErrorMapper(
    private val context: Context
): IFudgeTvErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is InvalidDataException -> R.string.generic_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}