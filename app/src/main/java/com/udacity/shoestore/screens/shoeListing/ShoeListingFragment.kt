package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding

class ShoeListingFragment : Fragment() {

    private lateinit var binding: ShoeListingFragmentBinding
    private lateinit var viewModel: ShoeListingViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_listing_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListingViewModel::class.java)
        binding.shoeListNextButton.setOnClickListener { navigateToDetailScreen(it) }
        return binding.root
    }

    private fun navigateToDetailScreen(it: View?) = findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
}