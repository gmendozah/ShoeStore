package com.udacity.shoestore.screens.shoeDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding

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


        binding.cancelButton.setOnClickListener { navigateToShoeList(it) }
        binding.saveButton.setOnClickListener { validateForm() }
        return binding.root
    }

    private fun validateForm(): Boolean {
        when {
            binding.nameEditText.text.isEmpty() -> {
                Toast.makeText(context, R.string.nameValidation, Toast.LENGTH_SHORT).show()
                return false
            }
            binding.sizeEditText.text.isEmpty() -> {
                Toast.makeText(context, R.string.sizeValidation, Toast.LENGTH_SHORT).show()
                return false
            }
            binding.companynEditText.text.isEmpty() -> {
                Toast.makeText(context, R.string.companyValidation, Toast.LENGTH_SHORT).show()
                return false
            }
            binding.descriptionEditText.text.isEmpty() -> {
                Toast.makeText(context, R.string.descriptionValidation, Toast.LENGTH_SHORT).show()
                return false
            }
            binding.sizeEditText.text.toString().toDouble() <= 0 -> {
                Toast.makeText(context, R.string.sizeValidationValue, Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }

    private fun navigateToShoeList(view: View) = view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}