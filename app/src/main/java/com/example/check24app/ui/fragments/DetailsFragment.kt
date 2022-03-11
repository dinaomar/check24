package com.example.check24app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.check24app.R
import com.example.check24app.databinding.FragmentDetailsBinding
import com.example.check24app.ui.fragments.all.AllProductsFragmentArgs
import java.text.SimpleDateFormat

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val args: AllProductsFragmentArgs by navArgs()
    val postFormater = SimpleDateFormat("MMM dd, yyyy")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.priceTextview.text = "Price: ${arguments!!.getDouble("price")} EUR"
        binding.descriptionTextView.text = args.description
        binding.nameTextView.text = args.name
        binding.releaseDateTv.text = postFormater.format(args.releaseDate)
        binding.ratingBar.rating = arguments!!.getDouble("rating").toFloat()
        binding.recipeImageView.load(args.imageUrl) {
            crossfade(600)
            error(R.mipmap.ic_launcher)
        }
    }

}