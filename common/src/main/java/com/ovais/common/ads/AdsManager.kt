package com.ovais.common.ads

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.ovais.common.ads.data.AdsConfig
import com.ovais.common.parser.ParsingService
import com.ovais.common.utils.default
import com.ovais.firebase.config.FirebaseRemoteConfigManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface AdsManager {
    fun initialize()
    val areAdsEnabled: Boolean
}

class DefaultAdsManager @Inject constructor(
    private val firebaseRemoteConfigManager: FirebaseRemoteConfigManager,
    private val parsingService: ParsingService,
    @ApplicationContext private val context: Context
) : AdsManager {


    private companion object {
        private const val ADS_CONFIG = "ads_config"
    }

    private val adsConfig: AdsConfig? by lazy {
        parsingService.decodeFromString(
            firebaseRemoteConfigManager.getString(ADS_CONFIG)
        )
    }

    override val areAdsEnabled: Boolean
        get() = adsConfig?.areAdsEnabled.default()

    override fun initialize() {
        MobileAds.initialize(context) {}
        if (adsConfig?.testDeviceIds.isNullOrEmpty().not()) {
            MobileAds.setRequestConfiguration(
                RequestConfiguration
                    .Builder()
                    .setTestDeviceIds(
                        adsConfig?.testDeviceIds.orEmpty()
                    )
                    .build()
            )
        }
    }
}