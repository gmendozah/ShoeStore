package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        setHasOptionsMenu(true)
        binding.logInExistingButton.setOnClickListener {
            navigateToWelcomeScreen(it)
        }
        binding.logInNewButton.setOnClickListener {
            navigateToWelcomeScreen(it)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToWelcomeScreen(view: View) = view.findNavController()
        .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
}