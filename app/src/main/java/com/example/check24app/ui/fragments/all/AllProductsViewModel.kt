package com.example.check24app.ui.fragments.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.check24app.data.repositories.ProductsRepository
import com.example.check24app.model.Products
import com.example.check24app.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    var productsResponse: MutableLiveData<NetworkResult<Products>> = MutableLiveData()

    fun getProducts() {
        viewModelScope.launch() {
            getProductsSafeCall()
        }
    }

    private suspend fun getProductsSafeCall() {
        try {
            val response = repository.remote.getProducts()
            productsResponse.value = handleRecipesResponse(response)
        } catch (e: Exception) {
            productsResponse.value = NetworkResult.Error("No Internet Connection!!")
        }

    }

    private fun handleRecipesResponse(response: Response<Products>): NetworkResult<Products>? {
        return when {
            response.isSuccessful -> {
                val productsResponse = response.body()
                NetworkResult.Success(productsResponse!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }
}