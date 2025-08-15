package com.example.kopilka.data

import kotlinx.coroutines.flow.Flow

class Repository(private val dao: OperationDao) {
    val balance: Flow<Long> = dao.balanceFlow()
    val history: Flow<List<Operation>> = dao.latest(200)
    suspend fun add(amountMinor: Long, note: String?) =
        dao.insert(Operation(amountMinor = amountMinor, note = note, type = OpType.ADD))
    suspend fun withdraw(amountMinor: Long, note: String?) =
        dao.insert(Operation(amountMinor = amountMinor, note = note, type = OpType.WITHDRAW))
}
