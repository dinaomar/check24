package com.example.check24app.data.repositories

import com.example.check24app.data.datasources.RemoteDataSource
import com.example.check24app.data.network.ProductsApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductsRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
) {
    val remote = remoteDataSource
}

