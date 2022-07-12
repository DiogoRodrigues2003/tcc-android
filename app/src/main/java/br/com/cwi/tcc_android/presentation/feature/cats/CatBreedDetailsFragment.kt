package br.com.cwi.tcc_android.presentation.feature.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.presentation.feature.pets.BreedDetailsViewHolder
import br.com.cwi.tcc_android.presentation.feature.pets.PetImageViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CatBreedDetailsFragment: Fragment() {

    private lateinit var binding: FragmentBreedDetailsBinding

    private val breedId by lazy {
        arguments?.getString("BREED_ID")
    }

    private val breedName by lazy {
        arguments?.getString("BREED_NAME")
    }

    private val viewModel: CatViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreedDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel(view)
    }

    private fun setUpViewModel(view: View) {
        viewModel.breedDetails.observe(viewLifecycleOwner) {
            BreedDetailsViewHolder(view, onAddPetClick = {
                navigateToAddPet()
            }).bind(this, it)
        }

        viewModel.catImage.observe(viewLifecycleOwner) {
            PetImageViewHolder(view, onNewImageClick = {
                viewModel.fetchCatImage(breedId)
            }).bind(this, it)
        }

        viewModel.fetchCatImage(breedId)
        viewModel.fetchBreedDetails(breedName)
    }

    private fun navigateToAddPet() {
        findNavController().navigate(
            R.id.addPetFragment,
            bundleOf(
                Pair("BREED_ID", breedId),
                Pair("BREED_NAME", breedName)
            )
        )
    }
}