package com.example.kopilka.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Insert
    suspend fun insert(op: Operation)

    @Delete
    suspend fun delete(op: Operation)

    @Query("SELECT * FROM operations ORDER BY timestamp DESC")
    fun allFlow(): Flow<List<Operation>>

    @Query("SELECT * FROM operations ORDER BY timestamp DESC LIMIT :limit")
    fun latest(limit: Int = 50): Flow<List<Operation>>

    @Query("SELECT COALESCE(SUM(CASE WHEN type = 'ADD' THEN amountMinor ELSE -amountMinor END), 0) FROM operations")
    fun balanceFlow(): Flow<Long>
}
