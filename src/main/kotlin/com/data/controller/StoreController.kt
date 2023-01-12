package com.data.controller

import com.data.model.Store
import com.data.port.StoreRedisPort
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller
class StoreController(private val storeRedisPort: StoreRedisPort) {

    @Get("/{id}")
    fun getOneStore(@Parameter id: String): MutableHttpResponse<Store>? {
        return HttpResponse.ok(storeRedisPort.getOneStore(id))
    }

    @Post
    fun insertStore(@Body store: Store): MutableHttpResponse<Store>? {
        return HttpResponse.created(storeRedisPort.insertStore(store))
    }

    @Delete("/{id}")
    fun deleteOneStore(@Parameter id: String): MutableHttpResponse<Long>? {
        return HttpResponse.ok(storeRedisPort.deleteOneStore(id))
    }
}