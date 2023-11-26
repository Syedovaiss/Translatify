package com.ovais.translator.worker

import androidx.work.WorkInfo
import com.ovais.common.utils.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.Serializable
import java.util.UUID


internal val List<WorkInfo>.running get() = filter { it.state == WorkInfo.State.RUNNING }

internal fun WorkInfo.toWorkResult(workTagData: (Set<String>) -> WorkTagData?): WorkInfoData {
    return WorkInfoData(
        state = when (state) {
            WorkInfo.State.ENQUEUED -> WorkState.Enqueued
            WorkInfo.State.RUNNING -> WorkState.Running
            WorkInfo.State.SUCCEEDED -> WorkState.Succeeded
            WorkInfo.State.FAILED -> WorkState.Failed
            WorkInfo.State.CANCELLED -> WorkState.Cancelled
            WorkInfo.State.BLOCKED -> WorkState.Blocked
        },
        data = outputData.keyValueMap,
        id = id,
        workTagData = workTagData(tags),
        progress = progress.getInt(PROGRESS_KEY, 0),
        error = outputData.getString(ERROR_KEY).default()
    )
}

data class WorkInfoData(
    val id: UUID,
    val workTagData: WorkTagData?,
    val state: WorkState,
    val progress: Int,
    val data: Map<String, Any>,
    val error: String
)

sealed interface WorkState {
    object Enqueued : WorkState
    object Running : WorkState
    object Cancelled : WorkState
    object Blocked : WorkState
    object Failed : WorkState
    object Succeeded : WorkState
}

@Serializable
data class WorkTagData(
    val type: WorkerType,
    val userTags: Set<String>
)

enum class WorkerType {
    COMPRESS,
    UPLOAD;
}


@OptIn(ExperimentalCoroutinesApi::class)
internal val limitedCoroutineContext = Dispatchers.IO.limitedParallelism(1)
internal const val PROGRESS_KEY = "progress"
internal const val ERROR_KEY = "error"