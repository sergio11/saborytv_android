package com.dreamsoftware.saborytv.ui.screens.chefprofiledetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.fudge.component.FudgeTvBackRowSchema
import com.dreamsoftware.fudge.component.FudgeTvCardDetails
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
internal fun ChefProfileDetailScreenContent(
    uiState: ChefProfileDetailUiState,
    actionListener: IChefProfileDetailActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            if (isLoading) {
                FudgeTvLoadingState(modifier = Modifier.fillMaxSize())
            } else if (chefProfileDetail == null) {
                FudgeTvNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.instructor_detail_not_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.instructor_detail_title,
                        textBold = true
                    )
                    FudgeTvCardDetails(
                        modifier = Modifier.width(400.dp),
                        title = chefProfileDetail.name,
                        description = chefProfileDetail.description,
                        imageUrl = chefProfileDetail.imageUrl
                    )
                    FudgeTvBackRowSchema(
                        onClickBack = actionListener::onBackPressed
                    )
                }
            }
        }
    }
}