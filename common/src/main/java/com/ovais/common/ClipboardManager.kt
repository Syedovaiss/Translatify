package com.ovais.common

import android.content.ClipData
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ClipboardManager {
    fun copy(text: CharSequence)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class DefaultClipboardManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ClipboardManager {
    private companion object {
        private const val LABEL_CLIPBOARD = "Copied"
    }

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    }

    override fun copy(text: CharSequence) {
        val clipData = ClipData.newPlainText(LABEL_CLIPBOARD, text)
        clipboardManager.setPrimaryClip(clipData)

    }
}