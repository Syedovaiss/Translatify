package com.ovais.translatify.options.domain

import com.ovais.common.SuspendUseCase
import com.ovais.translatify.R
import com.ovais.translatify.options.data.SettingOptionItems
import com.ovais.translatify.options.data.SettingOptionType
import javax.inject.Inject

interface GeneralSettingsUseCase : SuspendUseCase<List<SettingOptionItems>>

class DefaultGeneralSettingsUseCase @Inject constructor() : GeneralSettingsUseCase {
    override suspend fun invoke(): List<SettingOptionItems> {
        return listOf(
            SettingOptionItems(
                icon = R.drawable.ic_change_language,
                title = "Change App Language",
                type = SettingOptionType.AppLanguage
            ),
            SettingOptionItems(
                icon = R.drawable.ic_languages,
                title = "Add new language",
                type = SettingOptionType.Languages
            ),
            SettingOptionItems(
                icon = R.drawable.ic_notification,
                title = "Notification",
                type = SettingOptionType.Notification
            ),
            SettingOptionItems(
                icon = R.drawable.ic_feedback,
                title = "Feedback",
                type = SettingOptionType.Feedback
            )

        )
    }
}