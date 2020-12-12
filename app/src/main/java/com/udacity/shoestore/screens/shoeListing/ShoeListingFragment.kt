package com.udacity.shoestore.screens.shoeListing

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding

class ShoeListingFragment : Fragment() {

    private lateinit var binding: ShoeListingFragmentBinding
    private val viewModel: ShoeListingViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_listing_fragment, container, false)
        val shoeListingFragmentArgs by navArgs<ShoeListingFragmentArgs>()
        if (shoeListingFragmentArgs.shoe != null) {
            viewModel.addShoe(shoeListingFragmentArgs.shoe!!)
        }
        binding.shoeListingViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            var listText = ""
            for (shoe in shoeList) {
                listText += "Name: ${shoe.name}\nSize: ${shoe.size}\nCompany: ${shoe.company}\nDescription: ${shoe.name}\n\n"
            }
            binding.textItemList.text = listText
        })


        binding.shoeListNextButton.setOnClickListener {
            navigateToDetailScreen()
        }
        return binding.root
    }

    private fun navigateToDetailScreen() = findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
}