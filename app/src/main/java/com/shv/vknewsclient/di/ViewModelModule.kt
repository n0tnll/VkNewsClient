package com.shv.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.shv.vknewsclient.presentation.main.MainViewModel
import com.shv.vknewsclient.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(value = NewsFeedViewModel::class)
    fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}