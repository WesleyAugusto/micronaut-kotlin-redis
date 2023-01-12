package com.data.service

import com.data.model.Store
import com.data.port.StoreRedisPort
import com.google.gson.Gson
import io.lettuce.core.RedisClient
import jakarta.inject.Singleton

@Singleton
class StoreRedis(private val redisClient: RedisClient) : StoreRedisPort {
    val connection = redisClient.connect()
    val commands = connection.sync()
    val gson = Gson()

    override fun getOneStore(id: String): Store {
        val result = commands.get(id)
        return gson.fromJson(result, Store::class.java)
    }

    override fun insertStore(store: Store): Store {
        val result = gson.toJson(store)
        commands.set(store.id, result)
        return store
    }

    override fun deleteOneStore(id: String): Long {
        return commands.del(id)
    }

}