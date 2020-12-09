package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.shoestore.R

class ShoeListingFragment : Fragment() {

    private lateinit var viewModel: ShoeListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ShoeListingViewModel::class.java)
        return inflater.inflate(R.layout.shoe_listing_fragment, container, false)
    }
}