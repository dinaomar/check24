package com.example.check24app.model

data class Products(
    val filters: List<String>,
    val header: Header,
    val products: List<Product>
)