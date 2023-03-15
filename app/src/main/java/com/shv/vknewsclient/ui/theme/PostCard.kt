package com.shv.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shv.vknewsclient.R

@Composable
fun FeedScreen() {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    painter = painterResource(id = R.drawable.post_comunity_thumbnail),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "/dev/null",
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "14:00",
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
            Text(
                text = "кабаныч, когда узнал, что если сотрудникам не платить они умирают от голода",
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Image(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "208")
                Image(
                    painter = painterResource(id = R.drawable.ic_views_counter),
                    contentDescription = null
                )
                Text(text = "206")
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null
                )
                Text(text = "11")
                Image(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = null
                )
                Text(text = "491")
                Image(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFeedScreenLight() {
    VkNewsClientTheme(
        darkTheme = false
    ) {
        FeedScreen()
    }
}

@Preview
@Composable
fun PreviewFeedScreenDark() {
    VkNewsClientTheme(
        darkTheme = true
    ) {
        FeedScreen()
    }
}