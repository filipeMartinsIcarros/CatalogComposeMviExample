package com.example.composemvicatalogo.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchLifeStyleResponse (
    @SerializedName("results") val list : List<SearchLifeStyleItemModel>,
): Parcelable

@Parcelize
data class SearchLifeStyleItemModel (
    @SerializedName("makeId") val id : Int,
    @SerializedName("makeDescription") val make : String,
    @SerializedName("makeImageVersion") val makeImage : Int,
    @SerializedName("modelId") val modelId : Int,
    @SerializedName("modelDescription") val model : String,
    @SerializedName("modelImageVersion") val modelImageVersion : Int,
    @SerializedName("rating") val rating : Float,
    @SerializedName("trims") val trims : List<SearchLifeStyleItemModel>
): Parcelable

@Parcelize
data class TrimModel (
    @SerializedName("id") val id : Int,
    @SerializedName("description") val description : String,
    @SerializedName("price") val price : Float,
    @SerializedName("current") val current : Boolean,
    @SerializedName("initialYear") val initialYear : Int,
    @SerializedName("finalYear") val finalYear : Int
): Parcelable