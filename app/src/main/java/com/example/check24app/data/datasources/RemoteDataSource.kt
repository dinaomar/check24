package com.example.check24app.data.datasources

import com.example.check24app.data.network.ProductsApi
import com.example.check24app.model.Products
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val productsApi: ProductsApi) {

    suspend fun getProducts(): Response<Products> {
        return productsApi.getProducts()
    }
}