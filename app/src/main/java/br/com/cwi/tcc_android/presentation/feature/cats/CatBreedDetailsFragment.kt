package br.com.cwi.tcc_android.presentation.feature.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            BreedDetailsViewHolder(view).bind(it)
        }

        viewModel.catImage.observe(viewLifecycleOwner) {
            PetImageViewHolder(view, onNewImageClick = {
                viewModel.fetchCatImage(breedId)
            }).bind(this, it)
        }

        viewModel.fetchCatImage(breedId)
        viewModel.fetchBreedDetails(breedName)
    }
}