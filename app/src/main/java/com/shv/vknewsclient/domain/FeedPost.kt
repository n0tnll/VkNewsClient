package com.shv.vknewsclient.domain

import com.shv.vknewsclient.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:08",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    val contentImageResId: Int = R.drawable.post_content_image,
    var statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 916),
        StatisticItem(type = StatisticType.SHARES, count = 7),
        StatisticItem(type = StatisticType.COMMENTS, count = 11),
        StatisticItem(type = StatisticType.LIKES, count = 465)
    )
)
