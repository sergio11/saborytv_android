package com.dreamsoftware.saborytv.ui.utils

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.model.AvatarTypeEnum
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.SubscriptionBO

fun RecipeBO?.formatInfo(context: Context, fullDetails: Boolean = false): String =
    this?.run { "${preparationTime.formatPreparationTime(context)} | ${difficulty.value} | ${context.getString(R.string.serving_value, servings)} ${if(fullDetails) {
        " | $country | ${context.getString(R.string.ingredients_count_value, ingredients.size)}"
    } else {
        String.EMPTY
    }} " }.orEmpty()

fun Long.formatPreparationTime(context: Context) =
    context.getString(R.string.preparation_time_value, this)

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '

fun AvatarTypeEnum.toDrawableResource(): Int =
    when(this) {
        AvatarTypeEnum.BOY -> R.drawable.profile_avatar_boy
        AvatarTypeEnum.GIRL -> R.drawable.profile_avatar_girl
        AvatarTypeEnum.WOMAN -> R.drawable.profile_avatar_woman
        AvatarTypeEnum.MAN -> R.drawable.profile_avatar_man
    }

fun SubscriptionBO.formatPeriodTimeAndPrice(periodTime: String, price: String, context: Context): String =
    "${context.getString(R.string.free_trail)} $price / ${
        if (periodTime == "1")
            "${context.getString(R.string.month)}.\n"
        else
            "$periodTime \n${context.getString(R.string.months)}."
    }${context.getString(R.string.subscription_cancelled)}"

fun SubscriptionBO.formatPeriodTime(periodTime: String, context: Context): String =
    "$periodTime ${context.getString(R.string.month_subscription)}"