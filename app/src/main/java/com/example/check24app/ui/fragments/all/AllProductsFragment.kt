package com.example.check24app.ui.fragments.all

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.check24app.R
import com.example.check24app.ui.adapters.recycler.ProductsRecyclerAdapter
import com.example.check24app.utils.NetworkResult
import com.example.check24app.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllProductsFragment : Fragment() {

    private val mAdapter: ProductsRecyclerAdapter by lazy { ProductsRecyclerAdapter() }
    private lateinit var viewModel: AllProductsViewModel
    lateinit var productsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[AllProductsViewModel::class.java]
        return inflater.inflate(R.layout.all_products_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsRecyclerView = view.findViewById(R.id.products_rv)
        progressBar = view.findViewById(R.id.progress_bar)

        // init recycler view
        productsRecyclerView.let {
            it.adapter = mAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        // request data from remote
        requestApiData()
    }

    private fun requestApiData() {
        viewModel.getProducts()
        viewModel.productsResponse.observeOnce(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkResult.Success -> {
                    progressBar.visibility = View.GONE
                    result.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), result.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}