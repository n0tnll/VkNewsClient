package com.shv.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class ProfilesDto(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("photo_100") val photoUrl: String
)
