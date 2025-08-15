package com.example.kopilka.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ListItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kopilka.model.Currency
import com.example.kopilka.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(nav: NavController, settings: SettingsViewModel) {
    val currency by settings.currency.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Настройки") }) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Валюта")
            Currency.values().forEach { c ->
                ListItem(
                    headlineContent = { Text("${c.code} ${c.symbol}") },
                    leadingContent = {
                        RadioButton(selected = currency == c, onClick = { settings.setCurrency(c); nav.popBackStack() })
                    }
                )
            }
        }
    }
}
