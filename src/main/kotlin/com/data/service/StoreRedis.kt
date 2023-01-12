package com.data.service

import com.data.model.Store
import com.data.port.StoreRedisPort
import com.google.gson.Gson
import io.lettuce.core.RedisClient
import jakarta.inject.Singleton
import java.time.Duration

@Singleton
class StoreRedis(private val redisClient: RedisClient) : StoreRedisPort {
    val connection = redisClient.connect()
    val commands = connection.sync()
    val gson = Gson()

    override fun getAllStore(): List<Store> {
        val listStore = commands.scan().keys
        val result: MutableList<Store> = mutableListOf()
        listStore.map {
            val store = commands.get(it)
            result.add(gson.fromJson(store, Store::class.java))
        }
        return result
    }

    override fun getOneStore(id: String): Store {
        val result = commands.get(id)
        return gson.fromJson(result, Store::class.java)
    }

    override fun insertStore(store: Store): Store {
        val result = gson.toJson(store)
        commands.set(store.id, result)
        commands.expire(store.id, Duration.ofMinutes(30))
        return store
    }

    override fun deleteOneStore(id: String): Long {
        return commands.del(id)
    }

}