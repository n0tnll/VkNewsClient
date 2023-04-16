package com.shv.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class PostCommentsDto(
    @SerializedName("items") val comments: List<CommentDto>,
    @SerializedName("profiles") val profiles: List<ProfilesDto>
)
