package com.ovais.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject

interface ScopeProvider {
    val background: CoroutineScope
    val ui: CoroutineScope
}

class DefaultScopeProvider @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : ScopeProvider {
    override val background: CoroutineScope
        get() = CoroutineScope(Job() + dispatcherProvider.background)

    override val ui: CoroutineScope
        get() = CoroutineScope(Job() + dispatcherProvider.ui)
}