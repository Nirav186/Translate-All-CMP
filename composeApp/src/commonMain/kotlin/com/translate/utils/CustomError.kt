package com.translate.utils

data class CustomError<out T>(val data: T) : Error

