package com.dreamsoftware.saborytv.ui.screens.profiles.save

import com.dreamsoftware.saborytv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SaveProfileScreenActionListener: IFudgeTvScreenActionListener {

    fun onAliasChanged(alias: String)
    fun onPinChanged(pin: String)
    fun onAvatarTypeChanged(avatarType: AvatarTypeEnum)
    fun onSaveProfilePressed()
    fun onAdvanceConfigurationPressed()
    fun onCancelPressed()
}