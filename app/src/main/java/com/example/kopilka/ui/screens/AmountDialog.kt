package com.example.kopilka.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardOptions

@Composable
fun AmountDialog(
    title: String,
    onDismiss: () -> Unit,
    onSubmit: (amountMinor: Long, note: String?) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { value ->
                        // allow numbers and comma/dot
                        val filtered = value.replace("[^0-9.,]".toRegex(), "")
                        amount = filtered
                    },
                    label = { Text("Сумма") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Заметка (необязательно)") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val normalized = amount.replace(",", ".")
                val minor = try {
                    val value = normalized.toDouble()
                    Math.round(value * 100.0).toLong()
                } catch (e: Exception) { 0L }
                if (minor > 0) onSubmit(minor, note.ifBlank { null })
                else onDismiss()
            }) { Text("Сохранить") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Отмена") }
        }
    )
}
