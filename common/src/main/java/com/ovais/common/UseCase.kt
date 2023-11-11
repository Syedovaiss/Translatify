package com.ovais.common

interface UseCase<R> {
    operator fun invoke(): R
}

interface ParamUseCase<P, R> {
    operator fun invoke(param: P): R
}