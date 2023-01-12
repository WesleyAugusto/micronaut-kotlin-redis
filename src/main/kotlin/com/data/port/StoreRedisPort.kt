package com.data.port

import com.data.model.Store

interface StoreRedisPort {
    fun getAllStore(): List<Store>
    fun getOneStore(id: String): Store
    fun insertStore(store: Store): Store
    fun deleteOneStore(id: String): Long
}