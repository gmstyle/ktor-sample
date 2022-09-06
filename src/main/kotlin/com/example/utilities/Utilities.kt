package com.example.utilities

object Utilities {

    fun String.capitalize(): String {
        return when {
            this.isEmpty() -> this
            this.length == 1 -> this.uppercase()
            else -> this[0].titlecase() + this.substring(1).lowercase()
        }
    }
}
