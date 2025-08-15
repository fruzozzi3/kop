package com.example.kopilka.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kopilka.data.OpType
import com.example.kopilka.ui.components.GradientCard
import com.example.kopilka.viewmodel.MainViewModel
import com.example.kopilka.viewmodel.SettingsViewModel
import com.example.kopilka.ui.format.formatMoney

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    nav: NavController,
    vm: MainViewModel,
    settings: SettingsViewModel
) {
    val balance by vm.balance.collectAsState()
    val history by vm.history.collectAsState()
    val currency by settings.currency.collectAsState()

    var showAdd by remember { mutableStateOf(false) }
    var showWithdraw by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Копилка") },
                actions = {
                    IconButton(onClick = { nav.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
            ) {
                FloatingActionButton(onClick = { showWithdraw = true }) {
                    Icon(Icons.Default.Remove, contentDescription = "Withdraw")
                }
                FloatingActionButton(onClick = { showAdd = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            GradientCard(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Column {
                    Text("Баланс", color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        formatMoney(balance, currency),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text("Валюта: ${currency.code}", color = MaterialTheme.colorScheme.onPrimary)
                }
            }

            Text(
                text = "История",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            if (history.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Пока нет операций")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(history, key = { it.id }) { op ->
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Row(
                                Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(if (op.type == OpType.ADD) "Пополнение" else "Снятие",
                                        fontWeight = FontWeight.SemiBold)
                                    op.note?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                                }
                                Text(
                                    (if (op.type == OpType.ADD) "+" else "-") + formatMoney(op.amountMinor, currency)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAdd) {
        AmountDialog(
            title = "Добавить",
            onDismiss = { showAdd = false },
            onSubmit = { amountMinor, note ->
                vm.add(amountMinor, note); showAdd = false
            }
        )
    }
    if (showWithdraw) {
        AmountDialog(
            title = "Снять",
            onDismiss = { showWithdraw = false },
            onSubmit = { amountMinor, note ->
                vm.withdraw(amountMinor, note); showWithdraw = false
            }
        )
    }
}
