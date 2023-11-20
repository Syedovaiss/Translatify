package com.ovais.common.ads.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsConfig(
    @SerialName("areAdsEnabled")
    val areAdsEnabled: Boolean? = false,
    @SerialName("testDeviceIds")
    val testDeviceIds: List<String>? = emptyList()
)