package com.example.check24app.ui.fragments.all

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.renderscript.Float4
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.check24app.R
import com.example.check24app.databinding.AllProductsFragmentBinding
import com.example.check24app.model.Product
import com.example.check24app.ui.adapters.recycler.ProductsRecyclerAdapter
import com.example.check24app.utils.NetworkResult
import com.example.check24app.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllProductsFragment : Fragment(), ProductsRecyclerAdapter.onItemClick {

    private val mAdapter: ProductsRecyclerAdapter by lazy { ProductsRecyclerAdapter(this) }
    private lateinit var viewModel: AllProductsViewModel
    lateinit var productsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var binding: AllProductsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.all_products_fragment, container, false)
        viewModel = ViewModelProvider(this)[AllProductsViewModel::class.java]
        return binding.root
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
                    result.data?.let {
                        mAdapter.setData(it)
                        binding.header = it.header
                    }
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

    override fun itemClicked(item: Product) {
        navigateToDetailsFragment(item)
    }

    private fun navigateToDetailsFragment(item: Product) {
        val bundle = Bundle()
        bundle.putString("imageUrl", item.imageURL)
        bundle.putDouble("price", item.price.value)
        bundle.putString("currency", item.price.currency)
        bundle.putDouble("rating", item.rating)
        bundle.putString("description", item.description)
        bundle.putInt("release_date", item.releaseDate)
        bundle.putString("name", item.name)
        findNavController().navigate(R.id.detailsFragment, bundle)
    }


}