package com.ovais.translator.tranlation

import com.google.android.gms.tasks.Task
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.ovais.common.dtos.QueryResult
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


interface TranslationModelManager {

    suspend fun download(
        model: String
    ): QueryResult<Void>

    suspend fun delete(
        model: String
    ): QueryResult<Void>

    suspend fun get(): QueryResult<MutableSet<TranslateRemoteModel>>
}

class DefaultTranslationModelManager @Inject constructor() : TranslationModelManager {
    private val modelManager by lazy {
        RemoteModelManager.getInstance()
    }

    override suspend fun download(
        model: String
    ): QueryResult<Void> {
        val requestedModel = TranslateRemoteModel.Builder(model).build()
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        return modelManager.download(requestedModel, conditions).completeAsQueryResult()
    }

    override suspend fun delete(
        model: String
    ): QueryResult<Void> {
        val requestedModel = TranslateRemoteModel.Builder(model).build()
        return modelManager.deleteDownloadedModel(requestedModel).completeAsQueryResult()
    }

    override suspend fun get(): QueryResult<MutableSet<TranslateRemoteModel>> {
        return modelManager
            .getDownloadedModels(TranslateRemoteModel::class.java)
            .completeAsQueryResult()
    }
}


internal suspend fun <T> Task<T>.completeAsQueryResult(): QueryResult<T> {
    return suspendCancellableCoroutine { continuation ->
        addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(QueryResult.Success(task.result))
            } else {
                continuation.resume(QueryResult.Failed(task.exception?.message ?: ""))
            }
        }
    }
}