package com.example.check24app.ui.adapters.bindingadapter

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.check24app.R

class ProductsRowBinding {

    companion object {

        @BindingAdapter("setNumbers")
        @JvmStatic
        fun setNumbers(textView: TextView, value: Int) {
            textView.text = value.toString()
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            imageView.load(url) {
                crossfade(600)
                error(R.mipmap.ic_launcher)
            }
        }

        @BindingAdapter("setRating")
        @JvmStatic
        fun setRating(ratingbar: RatingBar, rating: Double) {
            ratingbar.rating = rating.toFloat()
        }

    }
}