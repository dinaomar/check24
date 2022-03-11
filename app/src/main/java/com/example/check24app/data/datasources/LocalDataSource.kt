package com.example.check24app.data.datasources

import com.example.check24app.data.database.ProductDao
import com.example.check24app.data.database.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao) {
    suspend fun insertRecipes(productEntity: ProductEntity) =
        productDao.addProduct(productEntity)


    fun readDatabase(): Flow<List<ProductEntity>> = productDao.readProducts()
}