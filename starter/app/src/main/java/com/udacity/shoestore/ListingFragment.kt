package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentListingBinding

class ListingFragment : Fragment() {
    private lateinit var binding: FragmentListingBinding
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_listing, container, false
        )
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailFragment)
        )

        viewModel.shoeList.observe(requireActivity(), Observer {
            it.forEach() { newShoe ->
                addShoeItem(newShoe.name, newShoe.company, newShoe.size, newShoe.description)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loginFragment -> {
                viewModel.allowOnBoarding()
            }
        }

        return NavigationUI.onNavDestinationSelected(item, this.findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun addShoeItem(
        shoeName: String,
        shoeCompany: String,
        shoeSize: Double,
        shoeDescription: String
    ) {

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val shoeListing = binding.shoeListing

        val shoeNameView = TextView(requireContext(), null, 0, R.style.title_2)
        shoeNameView.layoutParams = layoutParams
        shoeNameView.text = "${shoeCompany}, ${shoeName}, Size: ${shoeSize.toString()}"

        val shoeDescriptionView = TextView(requireContext(), null, 0, R.style.paragraph_1)
        shoeDescriptionView.layoutParams = layoutParams
        shoeDescriptionView.text = shoeDescription

        shoeListing.addView(shoeNameView)
        shoeListing.addView(shoeDescriptionView)
    }
}