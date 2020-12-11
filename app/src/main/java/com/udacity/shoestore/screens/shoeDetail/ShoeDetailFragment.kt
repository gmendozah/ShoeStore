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
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: ShoeDetailFragmentBinding
    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var viewModelFactory: ShoeDetailViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail_fragment, container, false)

        val shoeDetailFragmentArgs by navArgs<ShoeDetailFragmentArgs>()
        viewModelFactory = ShoeDetailViewModelFactory(shoeDetailFragmentArgs.shoeListDetail)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)

        setHasOptionsMenu(true)

        binding.shoeDetailViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cancelButton.setOnClickListener { navigateToShoeList(shoeList = viewModel.shoeList.value?.toTypedArray()) }
        binding.saveButton.setOnClickListener {
            if (viewModel.saveShoe() == 0) {
                navigateToShoeList(shoeList = viewModel.shoeList.value?.toTypedArray())
            }
        }
        viewModel.errorResource.observe(viewLifecycleOwner, Observer {
            if (it != null && it != 0) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    private fun navigateToShoeList(shoeList: Array<Shoe>? = null) = findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment(shoeList))

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}