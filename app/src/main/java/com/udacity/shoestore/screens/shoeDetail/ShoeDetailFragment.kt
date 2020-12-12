package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import androidx.lifecycle.Observer
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: ShoeDetailFragmentBinding
    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        setHasOptionsMenu(true)

        binding.shoeDetailViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cancelButton.setOnClickListener { navigateToShoeList() }

        viewModel.errorResource.observe(viewLifecycleOwner, Observer {
            if (it != null && it != 0) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.shoe.observe(viewLifecycleOwner, Observer { shoe ->
            navigateToShoeList(shoe = shoe)
        })

        return binding.root
    }

    private fun navigateToShoeList(shoe: Shoe? = null) = findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment(shoe))

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}