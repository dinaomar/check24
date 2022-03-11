package com.example.check24app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products_table ORDER BY id ASC")
    fun readProducts(): Flow<List<ProductEntity>>
}