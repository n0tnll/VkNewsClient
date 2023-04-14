package com.shv.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesCountResponseDto(
    @SerializedName("response") val likesCount: LikesCountDto
)
