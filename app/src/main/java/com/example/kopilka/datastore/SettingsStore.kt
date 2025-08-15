package com.example.kopilka.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.kopilka.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class SettingsStore(private val context: Context) {
    private val KEY_CURRENCY = stringPreferencesKey("currency")
    val currencyFlow: Flow<Currency> = context.dataStore.data.map { prefs ->
        Currency.fromCode(prefs[KEY_CURRENCY])
    }
    suspend fun setCurrency(currency: Currency) {
        context.dataStore.edit { it[KEY_CURRENCY] = currency.code }
    }
}
