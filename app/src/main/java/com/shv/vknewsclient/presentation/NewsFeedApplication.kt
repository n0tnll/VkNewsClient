package com.shv.vknewsclient.presentation

import android.app.Application
import com.shv.vknewsclient.di.ApplicationComponent
import com.shv.vknewsclient.di.DaggerApplicationComponent
import com.shv.vknewsclient.domain.entity.FeedPost

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this, FeedPost(
                0, 0, ",", "", "", ",", ",",
                listOf(), true
            )
        )
    }
}