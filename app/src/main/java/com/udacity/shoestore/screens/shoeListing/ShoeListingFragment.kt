package com.udacity.shoestore.screens.shoeListing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListingFragment : Fragment() {

    private lateinit var binding: ShoeListingFragmentBinding
    private lateinit var viewModel: ShoeListingViewModel
    private lateinit var viewModelFactory: ShoeListingViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_listing_fragment, container, false)
        val shoeListingFragmentArgs by navArgs<ShoeListingFragmentArgs>()
        viewModelFactory = ShoeListingViewModelFactory(shoeListingFragmentArgs.shoeList)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeListingViewModel::class.java)

        binding.shoeListingViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            Timber.i(shoeList.toString())
            var listText = ""
            for (shoe in shoeList) {
                listText += "Name: ${shoe.name}\nSize: ${shoe.size}\nCompany: ${shoe.company}\nDescription: ${shoe.name}\n\n"
            }
            binding.textItemList.text = listText
            Timber.e(listText)
        })


        binding.shoeListNextButton.setOnClickListener {
            navigateToDetailScreen(viewModel.shoeList.value ?: emptyArray())
        }
        return binding.root
    }

    private fun navigateToDetailScreen(shoeList: Array<Shoe>) = findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment(shoeList))
}