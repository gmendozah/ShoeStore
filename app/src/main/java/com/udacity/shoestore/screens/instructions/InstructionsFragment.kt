package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        setHasOptionsMenu(true)
        binding.instructionsNextBtn.setOnClickListener { navigateToShoeListingScreen(it) }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToShoeListingScreen(view: View) = view.findNavController()
            .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListingFragment(null))
}