package com.shv.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.shv.vknewsclient.presentation.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(value = CommentsViewModel::class)
    fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}