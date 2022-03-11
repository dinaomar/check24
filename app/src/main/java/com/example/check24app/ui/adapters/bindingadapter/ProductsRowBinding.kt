package com.example.check24app.ui.adapters.bindingadapter

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.check24app.R
import java.text.ParseException
import java.text.SimpleDateFormat

class ProductsRowBinding {

    companion object {

        @BindingAdapter("setNumbers")
        @JvmStatic
        fun setNumbers(textView: TextView, value: Double) {
            // this EUR should be dynamic in next stage :)
            val stringConcat =
                textView.context.getString(R.string.price) + ": " + value.toString() + "EUR"
            textView.text = stringConcat
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

        @BindingAdapter("setDate")
        @JvmStatic
        fun setDate(textView: TextView, release_date: Int) {
            var outputDate = ""
            try {
                val postFormater = SimpleDateFormat("MMM dd, yyyy")
                outputDate = postFormater.format(release_date)
                textView.text = outputDate
            } catch (e: ParseException) {
                e.printStackTrace()
                textView.text = ""
            }
        }

    }
}