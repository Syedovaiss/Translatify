package com.ovais.translatify.options.domain

import com.ovais.common.UseCase
import com.ovais.translatify.R
import com.ovais.translatify.options.data.SettingOptionItems
import com.ovais.translatify.options.data.SettingOptionType
import javax.inject.Inject

interface OtherSettingsUseCase : UseCase<List<SettingOptionItems>>

class DefaultOtherSettingsUseCase @Inject constructor() : OtherSettingsUseCase {

    override fun invoke(): List<SettingOptionItems> {
        return listOf(
            SettingOptionItems(
                icon = R.drawable.ic_terms_and_conditions,
                title = "Terms & Conditions",
                type = SettingOptionType.TermsAndConditions
            ),
            SettingOptionItems(
                icon = R.drawable.ic_privacy_policy,
                title = "Privacy Policy",
                type = SettingOptionType.PrivacyPolicy
            ),
            SettingOptionItems(
                icon = R.drawable.ic_about_us,
                title = "About Us",
                type = SettingOptionType.AboutUs
            ),
            SettingOptionItems(
                icon = R.drawable.ic_help_and_support,
                title = "Help & Support",
                type = SettingOptionType.HelpAndSupport
            )
        )
    }
}