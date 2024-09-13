package com.dreamsoftware.saborytv.ui.utils

import android.content.Context
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.model.AvatarTypeEnum
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeTypeEnum
import com.dreamsoftware.saborytv.domain.model.SubscriptionBO

fun RecipeBO?.formatTimeAndType(): String =
    this?.run { "${preparationTime.formatPreparationTime()} | $difficulty" }.orEmpty()

fun Long.formatPreparationTime(): String = "$this minutes"

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '


fun RecipeTypeEnum.getStartButtonID() = when (this) {
    RecipeTypeEnum.VEGETARIAN -> R.string.start_session
    RecipeTypeEnum.VEGAN -> R.string.start_program
    RecipeTypeEnum.GLUTEN_FREE -> R.string.start_workout
    RecipeTypeEnum.HIGH_PROTEIN -> R.string.start_workout
    RecipeTypeEnum.NOT_SET -> R.string.start_session
}

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