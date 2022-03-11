package com.example.check24app.ui.fragments.available

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.check24app.R

class AvialbleFragment : Fragment() {

    companion object {
        fun newInstance() = AvialbleFragment()
    }

    private lateinit var viewModel: AvialbleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.avialble_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AvialbleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}