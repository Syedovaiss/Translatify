package com.ovais.common.parser

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import javax.inject.Inject

class ParsingService @Inject constructor(val json: Json) {

    inline fun <reified T> decodeFromString(string: String): T? {
        return try {
            json.decodeFromString(string)
        } catch (e: Exception) {
            null
        }
    }

    inline fun <reified T> decodeFromStringOrThrow(string: String): T {
        return try {
            json.decodeFromString(string)
        } catch (e: Exception) {
            throw ParsingException(e.localizedMessage)
        }
    }

    inline fun <reified T> encodeToString(value: T): String {
        return json.encodeToString(value)
    }

    inline fun <reified T> decodeFromString(
        deserializationStrategy: DeserializationStrategy<T>,
        string: String
    ): T? {
        return try {
            json.decodeFromString(deserializationStrategy, string)
        } catch (e: Exception) {
            null
        }
    }

    inline fun <reified T> decodeFromJsonElement(jsonElement: JsonElement): T? {
        return try {
            json.decodeFromJsonElement(jsonElement)
        } catch (e: Exception) {
            null
        }
    }
}

class ParsingException(override val message: String?) : IllegalArgumentException(message)
