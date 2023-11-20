package com.ovais.common.translation

import android.util.Log
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import javax.inject.Inject

interface TranslationManager {
    fun translate(
        source: String,
        target: String,
        text: String,
        result: (String) -> Unit
    )
}

class DefaultTranslationManager @Inject constructor() : TranslationManager {

    override fun translate(
        source: String,
        target: String,
        text: String,
        result: (String) -> Unit
    ) {
        /*
        val remoteModel = RemoteModelManager.getInstance()
        remoteModel.getDownloadedModels(TranslateRemoteModel::class.java).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i("data---dm","$#${it.result}")
            } else {
                Log.i("data---","${it.exception}")
            }
        }
        */
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(source)
            .setTargetLanguage(target)
            .build()
        val translator = Translation.getClient(options)
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                Log.i("data----", "Downloaded")
            }
            .addOnFailureListener { exception ->
                Log.i("data----", "$exception")
            }
        translator.translate(text).addOnSuccessListener { translatedText ->
            result(translatedText)
            Log.i("data----", "Text: $translatedText")
        }.addOnFailureListener { exception ->
            Log.e("data---", "$exception")
        }
    }
}