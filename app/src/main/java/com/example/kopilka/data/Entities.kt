package com.example.kopilka.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class OpType { ADD, WITHDRAW }

@Entity(tableName = "operations")
data class Operation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amountMinor: Long, // amount in minor units (копейки/центы)
    val note: String? = null,
    val type: OpType,
    val timestamp: Long = System.currentTimeMillis()
)
