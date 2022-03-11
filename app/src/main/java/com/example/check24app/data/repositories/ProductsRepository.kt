package com.example.check24app.data.repositories

import com.example.check24app.data.datasources.LocalDataSource
import com.example.check24app.data.datasources.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductsRepository @Inject constructor(
    remoteDataSource: RemoteDataSource, localDatasource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDatasource
}

