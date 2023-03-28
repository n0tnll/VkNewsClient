package com.shv.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shv.vknewsclient.R
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.StatisticItem
import com.shv.vknewsclient.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onStatisticItemClickListener: (item: StatisticItem) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader(feedPost = feedPost)
            PostContent(feedPost = feedPost)
            Statistics(
                statistics = feedPost.statistics,
                onItemClickListener = onStatisticItemClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Thin,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun PostContent(
    feedPost: FeedPost
) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = feedPost.contentText,
        color = MaterialTheme.colors.onPrimary,
        fontSize = 14.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        painter = painterResource(id = feedPost.contentImageResId),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onItemClickListener: (item: StatisticItem) -> Unit
) {
    Row(
    ) {
        Row(modifier = Modifier.weight(1f)) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_counter,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(sharesItem)
                }
            )
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(commentItem)
                }
            )
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likesItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find {
        it.type == type
    } ?: throw IllegalStateException("getItemByType(): unknown item type: $type")
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}