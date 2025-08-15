package com.example.kopilka.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kopilka.datastore.SettingsStore
import com.example.kopilka.model.Currency
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application): AndroidViewModel(app) {
    private val store = SettingsStore(app)
    val currency = store.currencyFlow.stateIn(viewModelScope, SharingStarted.Eagerly, Currency.UAH)
    fun setCurrency(c: Currency) = viewModelScope.launch { store.setCurrency(c) }
}
