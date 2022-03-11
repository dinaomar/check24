package com.example.check24app.data.network

import com.example.check24app.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("/products-test.json")
    suspend fun getProducts(): Response<Products>

}