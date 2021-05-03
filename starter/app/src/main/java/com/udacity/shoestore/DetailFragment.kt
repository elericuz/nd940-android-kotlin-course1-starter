package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater, R.layout.fragment_detail, container, false
        )

        val navController = requireActivity().findNavController(R.id.navHostFragment)

        binding.cancelButton.setOnClickListener {
            navController.navigateUp()
        }

        binding.saveButton.setOnClickListener {
            viewModel.addShoe(
                binding.shoeName.text.toString(),
                binding.shoeCompany.text.toString(),
                binding.shoeSize.text.toString().toDouble(),
                binding.shoeDescription.text.toString()
            )
            navController.navigateUp()
        }

        return binding.root
    }
}