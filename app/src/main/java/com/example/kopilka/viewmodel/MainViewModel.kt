package com.example.kopilka.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kopilka.data.AppDatabase
import com.example.kopilka.data.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(app: Application): AndroidViewModel(app) {
    private val repo = Repository(AppDatabase.get(app).operationDao())

    val balance = repo.balance.stateIn(viewModelScope, SharingStarted.Lazily, 0L)
    val history = repo.history.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun add(amountMinor: Long, note: String?) = viewModelScope.launch {
        repo.add(amountMinor, note)
    }
    fun withdraw(amountMinor: Long, note: String?) = viewModelScope.launch {
        repo.withdraw(amountMinor, note)
    }
}
