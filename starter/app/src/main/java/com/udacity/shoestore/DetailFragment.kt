package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe

class DetailFragment : Fragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    lateinit var navController: NavController
    private lateinit var binding: FragmentDetailBinding
    private val shoe: Shoe = Shoe("", 0.00, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater, R.layout.fragment_detail, container, false
        )

        navController = requireActivity().findNavController(R.id.navHostFragment)

        binding.shoe = shoe
        binding.shoesViewModel = viewModel
        binding.detail = this

        return binding.root
    }

    fun addShoe() {
        binding.apply {
            if (shoe!!.name.isEmpty()) {
                Toast.makeText(context, R.string.must_add_a_model, Toast.LENGTH_SHORT).show()
            } else if (shoe!!.company.isEmpty()) {
                Toast.makeText(context, R.string.must_add_a_company, Toast.LENGTH_SHORT).show()
            } else if (shoe!!.size.isNaN() || (shoe!!.size == 0.0)) {
                Toast.makeText(context, R.string.must_add_a_size, Toast.LENGTH_SHORT).show()
            } else if (shoe!!.description.isEmpty()) {
                Toast.makeText(context, R.string.must_add_a_description, Toast.LENGTH_SHORT).show()
            } else {
                navController.navigateUp()
                viewModel.addShoe(shoe!!)
                invalidateAll()
            }
        }
    }
}