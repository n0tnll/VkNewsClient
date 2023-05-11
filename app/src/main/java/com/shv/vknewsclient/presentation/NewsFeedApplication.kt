package com.shv.vknewsclient.presentation

import android.app.Application
import com.shv.vknewsclient.di.ApplicationComponent
import com.shv.vknewsclient.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}