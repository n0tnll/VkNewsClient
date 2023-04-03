package com.shv.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.StatisticItem
import com.shv.vknewsclient.ui.theme.NavigationItem

class MainViewModel : ViewModel() {

    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem>
        get() = _selectedNavItem

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initialList)
    val feedPosts: LiveData<List<FeedPost>>
        get() = _feedPosts

    fun selectNavItem(navItem: NavigationItem) {
        _selectedNavItem.value = navItem
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val oldPost = feedPosts.value?.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPost.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }

    fun remove(feedPost: FeedPost) {
        val feedPostsList = feedPosts.value?.toMutableList() ?: mutableListOf()
        feedPostsList.remove(feedPost)
        _feedPosts.value = feedPostsList
    }
}