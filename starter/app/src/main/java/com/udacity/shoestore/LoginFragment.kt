package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        )

        binding.loginButton.setOnClickListener {
            if (viewModel.onBoarding.value!!) {
                this.findNavController().navigate(R.id.action_loginFragment_to_listingFragment)
            } else {
                this.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }

        binding.newAccountButton.setOnClickListener {
            if (viewModel.onBoarding.value!!) {
                this.findNavController().navigate(R.id.action_loginFragment_to_listingFragment)
            } else {
                this.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}