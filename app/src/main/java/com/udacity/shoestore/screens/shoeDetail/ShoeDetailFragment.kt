package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import androidx.lifecycle.Observer
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoeListing.ShoeListingViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: ShoeDetailFragmentBinding
    private lateinit var viewModel: ShoeDetailViewModel
    private val shoeListingViewModel: ShoeListingViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail_fragment, container, false)
        //This view model is used to validate the Shoe form
        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        setHasOptionsMenu(true)
        with(binding){
            shoeDetailViewModel = viewModel
            lifecycleOwner = this@ShoeDetailFragment
            cancelButton.setOnClickListener { navigateToShoeList() }
        }

        viewModel.errorResource.observe(viewLifecycleOwner, Observer {
            if (it != null && it != 0) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.shoe.observe(viewLifecycleOwner, Observer { shoe ->
            shoeListingViewModel.addShoe(shoe)
            navigateToShoeList()
        })

        return binding.root
    }

    private fun navigateToShoeList() = findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}