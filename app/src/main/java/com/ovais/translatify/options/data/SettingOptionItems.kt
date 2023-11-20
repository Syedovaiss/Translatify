package com.ovais.translatify.options.data

data class SettingOptionItems(
    val icon: Int,
    val title: String,
    val type: SettingOptionType
)

sealed interface SettingOptionType {
    object SignIn : SettingOptionType
    object AccountInfo : SettingOptionType
    object Languages : SettingOptionType
    object PrivacyPolicy : SettingOptionType
    object AppLanguage : SettingOptionType
    object TermsAndConditions : SettingOptionType
    object SignOut : SettingOptionType
    object Feedback : SettingOptionType
    object HelpAndSupport : SettingOptionType
    object Notification : SettingOptionType
    object AuthenticationMethod : SettingOptionType //Fingerprint - FaceId
    object AboutUs : SettingOptionType
    object PremiumUser : SettingOptionType
}
