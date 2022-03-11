package com.example.check24app.data.database

import androidx.room.TypeConverter
import com.example.check24app.model.Product
import com.example.check24app.model.Products
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: Product): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(string: String): Product {
        val listType = object : TypeToken<Products>() {}.type
        return gson.fromJson(string, listType)
    }
}