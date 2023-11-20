package com.ovais.translatify.options.domain

import com.ovais.common.SuspendLambdaUseCase
import com.ovais.common.storage.LocalStorageManager
import com.ovais.common.utils.default
import com.ovais.translatify.R
import com.ovais.translatify.options.data.SettingOptionItems
import com.ovais.translatify.options.data.SettingOptionType
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

interface AccountSettingsUseCase : SuspendLambdaUseCase<List<SettingOptionItems>>

class DefaultAccountSettingsUseCase @Inject constructor(
    private val localStorageManager: LocalStorageManager
) : AccountSettingsUseCase {


    override suspend fun invoke(completion: (List<SettingOptionItems>) -> Unit) {
        val option = arrayListOf<SettingOptionItems>()
        localStorageManager.isLoggedIn().collectLatest { isLoggedIn ->
            if (isLoggedIn.default()) {
                option.add(
                    SettingOptionItems(
                        icon = R.drawable.ic_profile,
                        title = "Account Information",
                        type = SettingOptionType.AccountInfo
                    )
                )
                option.add(
                    SettingOptionItems(
                        icon = R.drawable.ic_auth_methods,
                        title = "Security & Authentication",
                        type = SettingOptionType.AuthenticationMethod
                    )
                )
                option.add(
                    SettingOptionItems(
                        icon = R.drawable.ic_premium,
                        title = "Become a premium member",
                        type = SettingOptionType.PremiumUser
                    )
                )
                option.add(
                    SettingOptionItems(
                        icon = R.drawable.ic_logout,
                        title = "Sign Out",
                        type = SettingOptionType.SignOut
                    )
                )
                completion(option)
            } else {
                option.add(
                    SettingOptionItems(
                        icon = R.drawable.ic_user,
                        title = "Sign In",
                        type = SettingOptionType.SignIn
                    )
                )
                completion(option)
            }
        }
    }
}