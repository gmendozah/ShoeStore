package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.shoestore.R

class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        return inflater.inflate(R.layout.shoe_detail_fragment, container, false)
    }
}