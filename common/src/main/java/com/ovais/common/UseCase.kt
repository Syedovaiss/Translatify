package com.ovais.common

interface UseCase<R> {
    operator fun invoke(): R
}

interface SuspendUseCase<R> {
    suspend operator fun invoke(): R
}

interface SuspendLambdaUseCase<R> {
    suspend operator fun invoke(completion: (R) -> Unit)
}

interface ParamUseCase<P, R> {
    operator fun invoke(param: P): R
}