package com.ovais.common.toaster

import android.content.Context
import android.widget.Toast
import com.ovais.common.toaster.Toaster.Status
import com.ovais.common.utils.EMPTY_STRING
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ToastManager {
    fun showToast(
        title: String,
        description: String,
        duration: Int = Toast.LENGTH_LONG,
        status: Status
    )

    fun showToast(
        description: String,
        duration: Int = Toast.LENGTH_LONG,
        status: Status
    )
}

class DefaultToastManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ToastManager {

    override fun showToast(title: String, description: String, duration: Int, status: Status) {
        Toaster
            .Builder(context)
            .setTitle(title)
            .setDescription(description)
            .setDuration(duration)
            .setStatus(status)
            .show()
    }

    override fun showToast(description: String, duration: Int, status: Status) {
        Toaster
            .Builder(context)
            .setTitle(EMPTY_STRING)
            .setDescription(description)
            .setDuration(duration)
            .setStatus(status)
            .show()
    }

}