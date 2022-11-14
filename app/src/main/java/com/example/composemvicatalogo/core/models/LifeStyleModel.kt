package com.example.composemvicatalogo.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LifeStyleResponse (
    @SerializedName("ativo") val enabled : Boolean,
    @SerializedName("listaItens") val list : List<LifeStyleListItemModel>,
): Parcelable

@Parcelize
data class LifeStyleListItemModel (
    @SerializedName("id") val id : Int,
    @SerializedName("imagemVersao") val imageVersion : Int,
    @SerializedName("listaId") val listId : Int,
    @SerializedName("titulo") val title : String,
    @SerializedName("descricao") val description : String,
    @SerializedName("link") val link : String
): Parcelable