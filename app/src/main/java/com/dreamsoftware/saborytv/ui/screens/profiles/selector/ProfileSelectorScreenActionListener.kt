package com.dreamsoftware.saborytv.ui.screens.profiles.selector

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface ProfileSelectorScreenActionListener: IFudgeTvScreenActionListener {

    fun onProfileSelected(profileId: String)
    fun onAddProfilePressed()
    fun onProfileManagementPressed()
}