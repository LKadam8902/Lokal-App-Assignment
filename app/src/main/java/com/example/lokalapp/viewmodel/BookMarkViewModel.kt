package com.example.lokalapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokalapp.db.Job.BookMark
import com.example.lokalapp.db.Job.BookMarkDao
import kotlinx.coroutines.launch

class BookMarkViewModel(
    private val bkdao: BookMarkDao
):ViewModel() {


    fun addBookmark(bookmark: BookMark) {
        viewModelScope.launch {
            bkdao.insertBookMark(bookmark)
        }
    }


    fun getAllBookmark(): LiveData<List<BookMark?>> {
        val bookmark = MutableLiveData<List<BookMark?>>()
        viewModelScope.launch {
            bookmark.postValue(bkdao.getAllBookMark())
        }
        return bookmark
    }

    fun getBookmark(jobId: Int): LiveData<BookMark?> {
        val bookmark = MutableLiveData<BookMark?>()
        viewModelScope.launch {
            bookmark.postValue(bkdao.getBookMarkById(jobId))
        }
        return bookmark
    }
}