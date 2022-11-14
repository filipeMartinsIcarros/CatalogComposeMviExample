package com.example.composemvicatalogo.core.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: String)