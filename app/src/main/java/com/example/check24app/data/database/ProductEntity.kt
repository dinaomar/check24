package com.example.check24app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.check24app.model.Product
import com.example.check24app.utils.Constants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class ProductEntity(var products: Product) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}