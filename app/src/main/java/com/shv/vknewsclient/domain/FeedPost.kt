package com.shv.vknewsclient.domain

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.shv.vknewsclient.R
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable {

    companion object {

        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {
            override fun get(bundle: Bundle, key: String): FeedPost? {
                return if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable(key, FeedPost::class.java)
                } else {
                    bundle.getParcelable(key)
                }
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }

        }
    }
}
